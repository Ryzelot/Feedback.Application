package com.example.feedbackapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.feedbackapplication.Controller.ConectionHelper;
import com.example.feedbackapplication.Controller.Utility;
import com.example.feedbackapplication.Model.Plate;
import com.example.feedbackapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Feedbackadd extends AppCompatActivity {

    Button backbuttonfeedback, btnaddfeed;
    String selectedDay;
    String selectedPlate;
    Spinner spinnerDay, spinnerPlate;
    ConectionHelper conn;
    ArrayList<Plate> feedListPlate;
    String[] Weekdays = {"Lunes", "Martes" ,"Miércoles", "Jueves" ,"Viernes", "Sábado" ,"Domingo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbackadd);

        // Inicialización de la conexión a la base de datos
        conn = new ConectionHelper(getApplicationContext(),"bd_productos",null,1);

        backbuttonfeedback = findViewById(R.id.backbuttonfeedback);
        btnaddfeed = findViewById(R.id.btnaddfeed);
        spinnerDay = findViewById(R.id.spinnerDay);
        spinnerPlate = findViewById(R.id.spinnerPlate);

        //ArrayAdapter para crear un objeto que contendra (Contexto, Layout, Arrayadapter variable name)
        ArrayAdapter<String> weekadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Weekdays);
        weekadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Al spinner ya con id obtenida se le pasa el adapter
        spinnerDay.setAdapter(weekadapter);

        SQLiteDatabase db = conn.getReadableDatabase();
        Plate plate = null;
        feedListPlate = new ArrayList<Plate>();

        //La consulta se realiza mediante un objeto Cursor que ejecuta la consulta SQL:
        Cursor cursor = db.rawQuery("SELECT " + Utility.CAMPO_PLATENAME + " FROM " + Utility.TABLA_PLATES, null);
        while (cursor.moveToNext()) {
            plate = new Plate();
            plate.setPlateName(cursor.getString(0));
            feedListPlate.add(plate);
        }
        cursor.close();

        List<String> plateNames = feedListPlate.stream()
                .map(Plate::getPlateName)
                .collect(Collectors.toList());

        ArrayAdapter<String> plateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, plateNames);
        plateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPlate.setAdapter(plateAdapter);

        backbuttonfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Feedbackadd.this, MainFeedbackActivity.class);
                startActivity(intent);
            }
        });

        btnaddfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();

                Toast.makeText(Feedbackadd.this, selectedDay, Toast.LENGTH_SHORT).show();
                Toast.makeText(Feedbackadd.this, selectedPlate, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Feedbackadd.this, MainFeedbackActivity.class);
                intent.putExtra("Day", selectedDay);
                intent.putExtra("Plate", selectedPlate);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        // Obtener el día de la semana seleccionado
        selectedDay = spinnerDay.getSelectedItem().toString();

        // Obtener el nombre del plato seleccionado
        selectedPlate = spinnerPlate.getSelectedItem().toString();
    }
}