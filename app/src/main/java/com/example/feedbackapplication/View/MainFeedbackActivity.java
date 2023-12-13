package com.example.feedbackapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.feedbackapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainFeedbackActivity extends AppCompatActivity {
    Button backbuttonfeedback, btnaddfeed;
    ListView feedlist;
    String thisDay, thisPlate, combinedString;

    // Array fijo con los días de la semana
    String[] weekdays = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    String[] weekdays2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_feedback);


            backbuttonfeedback = findViewById(R.id.backbuttonfeedback);
            btnaddfeed = findViewById(R.id.btnaddfeed);
            feedlist = findViewById(R.id.feedlist);

            // Obtén el Intent que inició esta actividad
            Intent intent = getIntent();

            // Verifica si el Intent contiene el dato con la clave "clave_dato"
            if (intent.hasExtra("Day") && intent.hasExtra("Plate")) {
                // Obtén el dato String
                thisDay = intent.getStringExtra("Day");
                thisPlate = intent.getStringExtra("Plate");

                // Concatenar el día y el plato en una cadena
                combinedString = thisDay + " " + thisPlate;

                // Buscar la posición del día en el array fijo de días
                int position = getDayPosition(thisDay);
                Toast.makeText(this, "Tengo la posicion " + position, Toast.LENGTH_SHORT).show();

                if (position >= 0 && position < weekdays.length){
                    weekdays[position] = combinedString;
                    weekdays2 = Arrays.copyOf(weekdays,weekdays.length);
                }else {
                    weekdays2[position] = combinedString;
                }

                updateFeedUI();

            } else {
                    // Manejo si no se recibe el dato
                Toast.makeText(this, "No se recibio el String", Toast.LENGTH_SHORT).show();
            }



        backbuttonfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainFeedbackActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnaddfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainFeedbackActivity.this,Feedbackadd.class);
                startActivity(intent);
            }
        });
    }

    private int getDayPosition(String thisDay) {
        // Devolver la posición correspondiente en el array fijo de días
        for (int i = 0; i < weekdays.length; i++) {
            if (weekdays[i].equalsIgnoreCase(thisDay)) {
                return i;
            }
        }
        return -1; // Manejar el caso por defecto o error si es necesario
    };

    private void updateFeedUI() {

        String[] displayArray;
        // Utilizar weekdays2 si no es nulo y tiene elementos
        if (weekdays2 != null && weekdays2.length > 0) {
            displayArray = weekdays2;
        } else {
            displayArray = weekdays;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayArray);
        feedlist.setAdapter(adapter);
    }
}