package com.example.feedbackapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.feedbackapplication.R;

public class MainActivity extends AppCompatActivity {
    //Declaracion de elementos del layout
    Button supermercado,despensa,plato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Busqueda de las id del layout
        supermercado = findViewById(R.id.btnsupermercado);
        despensa = findViewById(R.id.btndespensa);
        plato = findViewById(R.id.btnplato);


        //Eventos de boton
        supermercado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainSupermarketActivity.class);
                startActivity(intent);
            }
        });

        despensa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainPantryActivity.class);
                startActivity(intent);
            }
        });

        plato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainPlateActivity.class);
                startActivity(intent);
            }
        });
    }
}