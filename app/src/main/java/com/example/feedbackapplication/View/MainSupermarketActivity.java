package com.example.feedbackapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.feedbackapplication.Controller.ConectionHelper;
import com.example.feedbackapplication.Controller.Utility;
import com.example.feedbackapplication.R;

public class MainSupermarketActivity extends AppCompatActivity {

    //Declaracion de elementos del layout
    Button backsuper, btnaddsuperlist, btnsupercheckoutlist, btnsuperupdatelist, btncleansuperlist;

    ConectionHelper conn; //Para limpiar la lista completa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_supermarket);

        // Inicialización de la conexión a la base de datos
        conn = new ConectionHelper(getApplicationContext(),"bd_productos",null,1);

        //Busqueda de las id del layout
        backsuper = findViewById(R.id.backsuper);
        btnaddsuperlist = findViewById(R.id.btnaddsuperlist);
        btnsupercheckoutlist = findViewById(R.id.btnsupercheckoutlist);
        btnsuperupdatelist = findViewById(R.id.btnsuperupdatelist);
        btncleansuperlist = findViewById(R.id.btncleansuperlist);



        //Eventos de boton
        backsuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainSupermarketActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnaddsuperlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainSupermarketActivity.this, Superadd.class);
                startActivity(intent);
            }
        });

        btnsupercheckoutlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainSupermarketActivity.this, Supercheckout.class);
                startActivity(intent);
            }
        });

        btnsuperupdatelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainSupermarketActivity.this, Superupdate.class);
                startActivity(intent);
            }
        });

        btncleansuperlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanSuperList();
            }
        });

    }

    private void cleanSuperList() {
        SQLiteDatabase db = conn.getWritableDatabase();

        // Elimina todas las filas de la tabla
        db.delete(Utility.TABLA_ITEMS, null,null);

        // Reinicia el índice autoincrementable
        String resetSql = "DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + Utility.TABLA_ITEMS + "'";
        db.execSQL(resetSql);

        Toast.makeText(getApplicationContext(), "Tu lista se ha eliminado por completo", Toast.LENGTH_LONG).show();

        db.close();
    }
}