package com.example.feedbackapplication.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.feedbackapplication.Controller.ConectionHelper;
import com.example.feedbackapplication.Controller.Utility;
import com.example.feedbackapplication.Model.ItemSuper;
import com.example.feedbackapplication.R;

import java.util.ArrayList;

public class Supercheckout extends AppCompatActivity {
    ListView superlista;
    ArrayList<String> listaInfo;
    ArrayList<ItemSuper> listaProducto;
    ConectionHelper conn;

    //Declaracion de elementos del layout
    Button backbuttonsupercheckout;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supercheckout);

        // Inicialización de la conexión a la base de datos
        conn = new ConectionHelper(getApplicationContext(),"bd_productos",null,1);

        //Busqueda de las id del layout
        backbuttonsupercheckout = findViewById(R.id.backbuttonsupercheckout);
        superlista = (ListView) findViewById(R.id.superlista);
        imageView = findViewById(R.id.imageView);


        checkoutProductList();

        //ArrayAdapter para MOSTRAR LA IN FORMACIÓN en la lista del ListView
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInfo);
        // Milistview.setAdapter(variable del ArrayAdapter)
        superlista.setAdapter(adaptador);


        //Eventos de clickeables
        backbuttonsupercheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Supercheckout.this, MainSupermarketActivity.class);
                startActivity(intent);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Supercheckout.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkoutProductList() {
        SQLiteDatabase db = conn.getReadableDatabase();
        ItemSuper itemsuper = null;
        listaProducto = new ArrayList<ItemSuper>();

        //La consulta se realiza mediante un objeto Cursor que ejecuta la consulta SQL:
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utility.TABLA_ITEMS, null);
        while (cursor.moveToNext()) {
            itemsuper = new ItemSuper();
            itemsuper.setId(cursor.getInt(0));
            itemsuper.setProducto(cursor.getString(1));
            itemsuper.setMarca(cursor.getString(2));
            itemsuper.setCantidad(cursor.getString(3));
            listaProducto.add(itemsuper);
        }
        earnList();
    }

    private void earnList() {
        listaInfo = new ArrayList<String>();
        for (int i=0; i<listaProducto.size(); i++){
            listaInfo.add(listaProducto.get(i).getId() + " - " + listaProducto.get(i).getProducto() + " - " + listaProducto.get(i).getMarca() + " - " + listaProducto.get(i).getCantidad());
        }
    }
}