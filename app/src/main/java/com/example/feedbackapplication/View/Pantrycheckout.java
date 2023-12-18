package com.example.feedbackapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.feedbackapplication.Controller.ConectionHelper;
import com.example.feedbackapplication.Controller.Utility;
import com.example.feedbackapplication.Model.ItemSuper;
import com.example.feedbackapplication.Model.Pantry;
import com.example.feedbackapplication.R;

import java.util.ArrayList;

public class Pantrycheckout extends AppCompatActivity {
    ListView pantrylista;
    ArrayList<String> listaInfoPantry;
    ArrayList<Pantry> listaPantry;
    ConectionHelper conn;
    Button backtopantrycheck;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantrycheckout);

        // Inicialización de la conexión a la base de datos
        conn = new ConectionHelper(getApplicationContext(),"bd_productos",null,1);

        backtopantrycheck = findViewById(R.id.backtopantrycheck);
        pantrylista = findViewById(R.id.pantrylista);
        imageView = findViewById(R.id.imageView);

        checkoutPantryList();

        //ArrayAdapter para MOSTRAR LA IN FORMACIÓN en la lista del ListView
        ArrayAdapter adaptador = new ArrayAdapter<String>(getApplicationContext(), R.layout.text_style_layout, listaInfoPantry);
        pantrylista.setAdapter(adaptador);

        backtopantrycheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantrycheckout.this, MainPantryActivity.class);
                startActivity(intent);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantrycheckout.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkoutPantryList() {

        SQLiteDatabase db = conn.getReadableDatabase();
        Pantry pantry = null;
        listaPantry = new ArrayList<Pantry>();

        //La consulta se realiza mediante un objeto Cursor que ejecuta la consulta SQL:
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utility.TABLA_PANTRY, null);
        while (cursor.moveToNext()) {
            pantry = new Pantry();
            pantry.setIdpantry(cursor.getInt(0));
            pantry.setItempantry(cursor.getString(1));
            pantry.setItembrandpantry(cursor.getString(2));
            pantry.setItemqtypantry(cursor.getString(3));
            listaPantry.add(pantry);
        }
        earnList();
    }

    private void earnList() {
        listaInfoPantry = new ArrayList<String>();
        for (int i=0; i<listaPantry.size(); i++){
            listaInfoPantry.add(listaPantry.get(i).getIdpantry() + " - " + listaPantry.get(i).getItemqtypantry() + " - " + listaPantry.get(i).getItempantry() + " - " + listaPantry.get(i).getItembrandpantry());
        }
    }
}