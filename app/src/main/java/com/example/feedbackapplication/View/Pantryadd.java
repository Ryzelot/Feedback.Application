package com.example.feedbackapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.feedbackapplication.Controller.ConectionHelper;
import com.example.feedbackapplication.Controller.Utility;
import com.example.feedbackapplication.R;

public class Pantryadd extends AppCompatActivity {

    Button backpantryadd,btnanotarpantry;
    EditText txtpantryitem, txtpantrybrand, txtpantryqty;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantryadd);

        backpantryadd = findViewById(R.id.backpantryadd);
        btnanotarpantry = findViewById(R.id.btnanotarpantry);
        txtpantryitem = findViewById(R.id.txtpantryitem);
        txtpantrybrand = findViewById(R.id.txtpantrybrand);
        txtpantryqty = findViewById(R.id.txtpantryqty);
        imageView = findViewById(R.id.imageView);


        backpantryadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantryadd.this, MainPantryActivity.class);
                startActivity(intent);
            }
        });

        btnanotarpantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToPantry();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantryadd.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addToPantry() {

        //Conexion a la base de datos
        ConectionHelper conn = new ConectionHelper(this,"bd_productos",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        // EXtraccion de los campos
        String itempantry = txtpantryitem.getText().toString().trim();
        String itempantrybrand = txtpantrybrand.getText().toString().trim();
        String itempantryqty = txtpantryqty.getText().toString().trim();

        Toast.makeText(this, "Tengo: " + itempantry + " " + itempantrybrand + " " + itempantryqty, Toast.LENGTH_SHORT).show();

        //Creacion del segundo contentvalue, es decir, 1 content por tabla (Content = Tabla)
        ContentValues tablePantry = new ContentValues();

        if (itempantry.isEmpty() || itempantrybrand.isEmpty() || itempantryqty.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Todos los campos deben ser completados", Toast.LENGTH_SHORT).show();
        } else {
            tablePantry.put(Utility.CAMPO_ITEM_PANTRY,txtpantryitem.getText().toString());
            tablePantry.put(Utility.CAMPO_ITEM_BRAND,txtpantrybrand.getText().toString());
            tablePantry.put(Utility.CAMPO_ITEM_QTY,txtpantryqty.getText().toString());

            Toast.makeText(this, "Se insertara: " + tablePantry, Toast.LENGTH_SHORT).show();

            //Insersion de datos en SQLite
            Long idResultante = db.insert(Utility.TABLA_PANTRY,Utility.CAMPO_ID_PANTRY, tablePantry);

            // Verifica el resultado de la inserción
            if (idResultante != -1) {
                // La inserción fue exitosa
                Toast.makeText(getApplicationContext(), "Se insertó correctamente. ID: " + idResultante, Toast.LENGTH_SHORT).show();
            } else {
                // La inserción falló
                Toast.makeText(getApplicationContext(), "Fallo la inserción.", Toast.LENGTH_SHORT).show();
            }
        }
        db.close();
    }
}