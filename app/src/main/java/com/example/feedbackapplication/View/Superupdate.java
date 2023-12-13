package com.example.feedbackapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.feedbackapplication.Controller.ConectionHelper;
import com.example.feedbackapplication.Controller.Utility;
import com.example.feedbackapplication.R;


public class Superupdate extends AppCompatActivity {
    //Declaracion de elementos del layout
    ImageView imageview;
    Button backbuttonsuperupdate, btnsearchsuper, btnupdsuper, btndeletesuper;
    EditText updsuperid, updsuperitem, updsuperbrand, updsuperqty;
    ConectionHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superupdate);

        //Conection to bd_productos
        conn = new ConectionHelper(getApplicationContext(),"bd_productos",null,1);

        //Busqueda de las id del layout
        backbuttonsuperupdate = findViewById(R.id.backbuttonsuperupdate);
        btnsearchsuper = findViewById(R.id.btnsearchsuper);
        btnupdsuper = findViewById((R.id.btnupdsuper));
        btndeletesuper = findViewById(R.id.btndeletesuper);
        updsuperid = findViewById(R.id.updsuperid);
        updsuperitem = findViewById(R.id.updsuperitem);
        updsuperbrand = findViewById(R.id.updsuperbrand);
        updsuperqty = findViewById(R.id.updsuperqty);
        imageview = findViewById(R.id.imageView);

        //Eventos de boton
        backbuttonsuperupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Superupdate.this, MainSupermarketActivity.class);
                startActivity(intent);
            }
        });

        btnsearchsuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchSuperItem();
            }
        });

        btnupdsuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSuperList();
            }
        });

        btndeletesuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSuperList();
            }
        });

        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Superupdate.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void deleteSuperList() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {updsuperid.getText().toString()};

        db.delete(Utility.TABLA_ITEMS, Utility.CAMPO_ID + "=?", parametros);

        Toast.makeText(getApplicationContext(), "El item se ha removido de tu lista", Toast.LENGTH_LONG).show();

        updsuperid.setText("");

        cleaner();
        db.close();
    }

    private void updateSuperList() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parameters = {updsuperid.getText().toString()};

        ContentValues values = new ContentValues();
        values.put(Utility.CAMPO_PRODUCTO, updsuperitem.getText().toString());
        values.put(Utility.CAMPO_MARCA, updsuperbrand.getText().toString());
        values.put(Utility.CAMPO_CT,updsuperqty.getText().toString());

        db.update(Utility.TABLA_ITEMS, values, Utility.CAMPO_ID + "=?", parameters);
        
        Toast.makeText(getApplicationContext(), "Tu lista se ha actualizado", Toast.LENGTH_LONG).show();
        
        cleaner();
        db.close();
    }

    private void searchSuperItem() {

        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {updsuperid.getText().toString()};

        try {
            Cursor cursor = db.rawQuery("SELECT " + Utility.CAMPO_PRODUCTO + ", "
                    + Utility.CAMPO_MARCA + ", " + Utility.CAMPO_CT + " FROM "
                    + Utility.TABLA_ITEMS + " WHERE "
                    + Utility.CAMPO_ID + " = ? ", parametros);


            cursor.moveToFirst();
            updsuperitem.setText(cursor.getString(0));
            updsuperbrand.setText(cursor.getString(1));
            updsuperqty.setText(cursor.getString(2));

            Toast.makeText(this, "Item encontrado", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Ups! Ese ID no existe en tu lista",
                    Toast.LENGTH_LONG).show();
            cleaner();
        }
    }

    private void cleaner() {
        updsuperid.setText("");
        updsuperitem.setText("");
        updsuperbrand.setText("");
        updsuperqty.setText("");
    }
}