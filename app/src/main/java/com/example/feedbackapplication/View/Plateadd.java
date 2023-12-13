package com.example.feedbackapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
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

public class Plateadd extends AppCompatActivity {

    ImageView imageView;
    Button backplateadd, btnplateaddtolist;
    EditText txtplatename, txtplateing1, txtplateing2, txtplateing3, txtplateing4, txtplateing5, txtplateing6, txtplateing7, txtplateing8, txtplateing9, txtplateing10,txtcondiment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plateadd);

        backplateadd = findViewById(R.id.backplateadd);
        btnplateaddtolist = findViewById(R.id.btnplateaddtolist);
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
                Intent intent = new Intent(Plateadd.this,MainPlateActivity.class);
                startActivity(intent);
            }
        });

        btnplateaddtolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToPlateList();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Plateadd.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addToPlateList() {
        //Conexion a la base de datos
        ConectionHelper conn = new ConectionHelper(this,"bd_productos",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        // EXtraccion de los campos
        String platename = txtplatename.getText().toString().trim();
        String ingredient1 = txtplateing1.getText().toString().trim();
        String ingredient2 = txtplateing2.getText().toString().trim();
        String ingredient3 = txtplateing3.getText().toString().trim();
        String ingredient4 = txtplateing4.getText().toString().trim();
        String ingredient5 = txtplateing5.getText().toString().trim();
        String ingredient6 = txtplateing6.getText().toString().trim();
        String ingredient7 = txtplateing7.getText().toString().trim();
        String ingredient8 = txtplateing8.getText().toString().trim();
        String ingredient9 = txtplateing9.getText().toString().trim();
        String ingredient10 = txtplateing10.getText().toString().trim();
        String condiment = txtcondiment.getText().toString().trim();


        //Creacion del segundo contentvalue, es decir, 1 content por tabla (Content = Tabla)
        ContentValues tablePlates = new ContentValues();

        if (platename.isEmpty() && ingredient1.isEmpty() && ingredient2.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Debes ingresar al menos el nombre del plato y 2 ingredientes", Toast.LENGTH_LONG).show();
        } else {
            tablePlates.put(Utility.CAMPO_PLATENAME,txtplatename.getText().toString());
            tablePlates.put(Utility.CAMPO_INGREDIENT1,txtplateing1.getText().toString());
            tablePlates.put(Utility.CAMPO_INGREDIENT2,txtplateing2.getText().toString());
            tablePlates.put(Utility.CAMPO_INGREDIENT3,txtplateing3.getText().toString());
            tablePlates.put(Utility.CAMPO_INGREDIENT4,txtplateing4.getText().toString());
            tablePlates.put(Utility.CAMPO_INGREDIENT5,txtplateing5.getText().toString());
            tablePlates.put(Utility.CAMPO_INGREDIENT6,txtplateing6.getText().toString());
            tablePlates.put(Utility.CAMPO_INGREDIENT7,txtplateing7.getText().toString());
            tablePlates.put(Utility.CAMPO_INGREDIENT8,txtplateing8.getText().toString());
            tablePlates.put(Utility.CAMPO_INGREDIENT9,txtplateing9.getText().toString());
            tablePlates.put(Utility.CAMPO_INGREDIENT10,txtplateing10.getText().toString());
            tablePlates.put(Utility.CAMPO_CONDIMENT,txtcondiment.getText().toString());

            //Insersion de datos en SQLite
            Long idResultante = db.insert(Utility.TABLA_PLATES,Utility.CAMPO_ID, tablePlates);
        }

        cleaner();
        Toast.makeText(getApplicationContext(),"Bon appetit! Plato agreado" , Toast.LENGTH_LONG).show(); //Mensaje emergente
        db.close();
    }

    private void cleaner() {
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