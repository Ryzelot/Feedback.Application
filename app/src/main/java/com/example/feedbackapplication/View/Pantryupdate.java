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

public class Pantryupdate extends AppCompatActivity {

    Button backbuttonpantryupdate, btnsearchpantry, btnupdpantry, btndeletepantry;
    EditText updpantryid, updpantryitem, updpantrybrand, updpantryqty;
    ImageView imageView;

    ConectionHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantryupdate);

        //Conection to bd_productos
        conn = new ConectionHelper(getApplicationContext(),"bd_productos",null,1);

        backbuttonpantryupdate = findViewById(R.id.backbuttonpantryupdate);
        btnsearchpantry = findViewById(R.id.btnsearchpantry);
        btnupdpantry = findViewById(R.id.btnupdpantry);
        btndeletepantry = findViewById(R.id.btndeletepantry);
        updpantryid = findViewById(R.id.updpantryid);
        updpantryitem = findViewById(R.id.updpantryitem);
        updpantrybrand = findViewById(R.id.updpantrybrand);
        updpantryqty = findViewById(R.id.updpantryqty);
        imageView = findViewById(R.id.imageView);

        backbuttonpantryupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantryupdate.this, MainPantryActivity.class);
                startActivity(intent);
            }
        });

        btnsearchpantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPantryItem();
            }
        });

        btnupdpantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePantryItem();
            }
        });

        btndeletepantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePantryItem();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pantryupdate.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void deletePantryItem() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parameters = {updpantryid.getText().toString()};

        db.delete(Utility.TABLA_PANTRY, Utility.CAMPO_ID_PANTRY + "=?", parameters);

        Toast.makeText(getApplicationContext(), "El item se ha removido de tu lista", Toast.LENGTH_LONG).show();

        updpantryid.setText("");

        cleaner();
        db.close();
    }

    private void updatePantryItem() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parameters = {updpantryid.getText().toString()};

        ContentValues values = new ContentValues();
        values.put(Utility.CAMPO_ITEM_PANTRY, updpantryitem.getText().toString());
        values.put(Utility.CAMPO_ITEM_BRAND, updpantrybrand.getText().toString());
        values.put(Utility.CAMPO_ITEM_QTY,updpantryqty.getText().toString());

        db.update(Utility.TABLA_PANTRY, values, Utility.CAMPO_ID_PANTRY + "=?", parameters);

        Toast.makeText(getApplicationContext(), "Tu lista se ha actualizado", Toast.LENGTH_LONG).show();

        cleaner();
        db.close();
    }

    private void searchPantryItem() {

        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {updpantryid.getText().toString()};

        try {
            Cursor cursor = db.rawQuery("SELECT " + Utility.CAMPO_ITEM_PANTRY + ", "
                    + Utility.CAMPO_ITEM_BRAND + ", " + Utility.CAMPO_ITEM_QTY + " FROM "
                    + Utility.TABLA_PANTRY + " WHERE "
                    + Utility.CAMPO_ID_PANTRY + " = ? ", parametros);


            cursor.moveToFirst();
            updpantryitem.setText(cursor.getString(0));
            updpantrybrand.setText(cursor.getString(1));
            updpantryqty.setText(cursor.getString(2));

            Toast.makeText(this, "Item encontrado", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Ups! Ese ID no existe en tu lista",
                    Toast.LENGTH_LONG).show();
            cleaner();
        }
    }

    private void cleaner() {
        updpantryid.setText("");
        updpantryitem.setText("");
        updpantrybrand.setText("");
        updpantryqty.setText("");
    }
}