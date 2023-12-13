package com.example.feedbackapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.feedbackapplication.R;

public class MainPlateActivity extends AppCompatActivity {

    Button backbuttonplate, btnaddplate, btnshowtplate, btneditplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_plate);

        backbuttonplate = findViewById(R.id.backbuttonplate);
        btnaddplate = findViewById(R.id.btnaddplate);
        btnshowtplate = findViewById(R.id.btnshowtplate);
        btneditplate = findViewById(R.id.btneditplate);

        backbuttonplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPlateActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnaddplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPlateActivity.this,Plateadd.class);
                startActivity(intent);
            }
        });

        btnshowtplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPlateActivity.this,Platecheckout.class);
                startActivity(intent);
            }
        });

        btneditplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPlateActivity.this, Plateupdate.class);
                startActivity(intent);
            }
        });
    }
}