package com.example.feedbackapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.feedbackapplication.R;

public class MainPantryActivity extends AppCompatActivity {

    Button backpantry, btnaddtopantry, btnupdatepantry, btncheckoutpantry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pantry);

        backpantry = findViewById(R.id.backtomain);
        btnaddtopantry = findViewById(R.id.btnaddtopantry);
        btncheckoutpantry = findViewById(R.id.btncheckoutpantry);
        btnupdatepantry = findViewById(R.id.btnupdatepantry);

        backpantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPantryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnaddtopantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPantryActivity.this, Pantryadd.class);
                startActivity(intent);
            }
        });

        btncheckoutpantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPantryActivity.this, Pantrycheckout.class);
                startActivity(intent);
            }
        });

        btnupdatepantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPantryActivity.this,Pantryupdate.class);
                startActivity(intent);
            }
        });
    }
}