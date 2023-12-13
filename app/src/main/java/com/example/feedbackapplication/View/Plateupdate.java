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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.feedbackapplication.Controller.ConectionHelper;
import com.example.feedbackapplication.Controller.Utility;
import com.example.feedbackapplication.R;

public class Plateupdate extends AppCompatActivity {
    ImageView imageView;
    Button backplateadd, btnplatesearchlist, btnplateupdatelist, btnplatedeletelist;
    EditText txtplateid, txtplatename, txtplateing1, txtplateing2, txtplateing3, txtplateing4, txtplateing5, txtplateing6, txtplateing7, txtplateing8, txtplateing9, txtplateing10,txtcondiment;

    ConectionHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plateupdate);

        //Conection to bd_productos
        conn = new ConectionHelper(getApplicationContext(),"bd_productos",null,1);

        backplateadd = findViewById(R.id.backplateadd);
        btnplatesearchlist = findViewById(R.id.btnplatesearchlist);
        btnplateupdatelist = findViewById(R.id.btnplateupdatelist);
        btnplatedeletelist = findViewById(R.id.btnplatedeletelist);
        txtplateid = findViewById(R.id.txtplateid);
        txtplatename = findViewById(R.id.txtplatename);
        txtplateing1 = findViewById(R.id.txtplateing1);
        txtplateing2 = findViewById(R.id.txtplateing2);
        txtplateing3 = findViewById(R.id.txtplateing3);
        txtplateing4 = findViewById(R.id.txtplateing4);
        txtplateing5 = findViewById(R.id.txtplateing5);
        txtplateing6 = findViewById(R.id.txtplateing6);
        txtplateing7 = findViewById(R.id.txtplateing7);
        txtplateing8 = findViewById(R.id.txtplateing8);
        txtplateing9 = findViewById(R.id.txtplateing9);
        txtplateing10 = findViewById(R.id.txtplateing10);
        txtcondiment = findViewById(R.id.txtplatecondiment);
        imageView = findViewById(R.id.imageView);


        backplateadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plateupdate.this, MainPlateActivity.class);
                startActivity(intent);
            }
        });

        btnplatesearchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPlateItem();
            }
        });

        btnplateupdatelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePlateList();
            }
        });

        btnplatedeletelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePlate();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plateupdate.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void deletePlate() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {txtplateid.getText().toString()};

        db.delete(Utility.TABLA_PLATES, Utility.CAMPO_ID + "=?", parametros);


        Toast.makeText(getApplicationContext(), "El plato se ha removido de tu lista", Toast.LENGTH_LONG).show();

        txtplateid.setText("");

        String resetSql = "DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + Utility.TABLA_PLATES + "'";
        db.execSQL(resetSql);

        cleaner();
        db.close();
    }

    private void updatePlateList() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parameters = {txtplateid.getText().toString()};

        ContentValues tablePlates = new ContentValues();
        tablePlates.put(Utility.CAMPO_PLATENAME, txtplatename.getText().toString());
        tablePlates.put(Utility.CAMPO_INGREDIENT1, txtplateing1.getText().toString());
        tablePlates.put(Utility.CAMPO_INGREDIENT2, txtplateing2.getText().toString());
        tablePlates.put(Utility.CAMPO_INGREDIENT3, txtplateing3.getText().toString());
        tablePlates.put(Utility.CAMPO_INGREDIENT4, txtplateing4.getText().toString());
        tablePlates.put(Utility.CAMPO_INGREDIENT5, txtplateing5.getText().toString());
        tablePlates.put(Utility.CAMPO_INGREDIENT6, txtplateing6.getText().toString());
        tablePlates.put(Utility.CAMPO_INGREDIENT7, txtplateing7.getText().toString());
        tablePlates.put(Utility.CAMPO_INGREDIENT8, txtplateing8.getText().toString());
        tablePlates.put(Utility.CAMPO_INGREDIENT9, txtplateing9.getText().toString());
        tablePlates.put(Utility.CAMPO_INGREDIENT10, txtplateing10.getText().toString());
        tablePlates.put(Utility.CAMPO_CONDIMENT, txtcondiment.getText().toString());

        db.update(Utility.TABLA_PLATES, tablePlates, Utility.CAMPO_ID + "=?", parameters);

        Toast.makeText(getApplicationContext(), "Tu plato se ha actualizado", Toast.LENGTH_LONG).show();

        cleaner();
        db.close();
    }

    private void searchPlateItem() {

        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {txtplateid.getText().toString()};

        try {
            Cursor cursor = db.rawQuery("SELECT " + Utility.CAMPO_ID_PLATE+ "," + Utility.CAMPO_PLATENAME + ","
                    + Utility.CAMPO_INGREDIENT1 + "," + Utility.CAMPO_INGREDIENT2 + "," + Utility.CAMPO_INGREDIENT3 + "," + Utility.CAMPO_INGREDIENT4 + "," + Utility.CAMPO_INGREDIENT5 + ","
                    + Utility.CAMPO_INGREDIENT6 + "," + Utility.CAMPO_INGREDIENT7 + "," + Utility.CAMPO_INGREDIENT8 + "," + Utility.CAMPO_INGREDIENT9 + "," + Utility.CAMPO_INGREDIENT10 + ","
                    + Utility.CAMPO_CONDIMENT + " FROM " + Utility.TABLA_PLATES + " WHERE " + Utility.CAMPO_ID + "=? ", parametros);

            cursor.moveToFirst();
            txtplateid.setText(cursor.getString(0));
            txtplatename.setText(cursor.getString(1));
            txtplateing1.setText(cursor.getString(2));
            txtplateing2.setText(cursor.getString(3));
            txtplateing3.setText(cursor.getString(4));
            txtplateing4.setText(cursor.getString(5));
            txtplateing5.setText(cursor.getString(6));
            txtplateing6.setText(cursor.getString(7));
            txtplateing7.setText(cursor.getString(8));
            txtplateing8.setText(cursor.getString(9));
            txtplateing9.setText(cursor.getString(10));
            txtplateing10.setText(cursor.getString(11));
            txtcondiment.setText(cursor.getString(12));

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Ups! Ese ID no existe en tu lista",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void cleaner() {
        txtplateid.setText("");
        txtplatename.setText("");
        txtplateing1.setText("");
        txtplateing2.setText("");
        txtplateing3.setText("");
        txtplateing4.setText("");
        txtplateing5.setText("");
        txtplateing6.setText("");
        txtplateing7.setText("");
        txtplateing8.setText("");
        txtplateing9.setText("");
        txtplateing10.setText("");
        txtcondiment.setText("");
    }
}