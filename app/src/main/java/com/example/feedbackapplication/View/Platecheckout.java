package com.example.feedbackapplication.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.feedbackapplication.Controller.ConectionHelper;
import com.example.feedbackapplication.Controller.Utility;
import com.example.feedbackapplication.Model.ItemSuper;
import com.example.feedbackapplication.Model.Pantry;
import com.example.feedbackapplication.Model.Plate;
import com.example.feedbackapplication.R;

import java.util.ArrayList;

public class Platecheckout extends AppCompatActivity {
    ListView platelist;
    ArrayList<String> listaInfoPlate;
    ArrayList<Plate> listaPlate;
    ConectionHelper conn;
    Button backbuttonplatecheckout;
    ImageView imageView;
    private String platedetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platecheckout);

        // Inicialización de la conexión a la base de datos
        conn = new ConectionHelper(getApplicationContext(),"bd_productos",null,1);

        backbuttonplatecheckout = findViewById(R.id.backbuttonplatecheckout);
        platelist = findViewById(R.id.platelist);
        imageView = findViewById(R.id.imageView);

        checkoutPlateList();

        backbuttonplatecheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Platecheckout.this, MainPlateActivity.class);
                startActivity(intent);
            }
        });

        platelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Ajusta la posicion del listview dado que la tabla comienza en 0 y el listview en 1; al ser +1 entonces 0+1 =1 y asi sucesivamente
                int adjustedPosition = position + 1;
                platedetails = extrationSqlite(adjustedPosition);
                showDetails();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Platecheckout.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private String extrationSqlite(int position) {
        //Llamado a la base de datos para extaer la informacion de la tabla en cuestion
        SQLiteDatabase db = conn.getReadableDatabase();
        String consulta = "SELECT * FROM " + Utility.TABLA_PLATES + " WHERE " + Utility.CAMPO_ID_PLATE + " = ?";
        Cursor cursor = db.rawQuery(consulta, new String[]{String.valueOf(position)});


        // Procesar el cursor y obtener la información
        String platedetails = "";
        if (cursor.moveToFirst()) {
            // Suponiendo que las columnas en la tabla son: CAMPO_ID, CAMPO_PRODUCTO, CAMPO_MARCA, CAMPO_CT
            int indiceId = cursor.getColumnIndex(Utility.CAMPO_ID_PLATE);
            int indicePlatename = cursor.getColumnIndex(Utility.CAMPO_PLATENAME);
            int indiceIngredient1 = cursor.getColumnIndex(Utility.CAMPO_INGREDIENT1);
            int indiceIngredient2 = cursor.getColumnIndex(Utility.CAMPO_INGREDIENT2);
            int indiceIngredient3 = cursor.getColumnIndex(Utility.CAMPO_INGREDIENT3);
            int indiceIngredient4 = cursor.getColumnIndex(Utility.CAMPO_INGREDIENT4);
            int indiceIngredient5 = cursor.getColumnIndex(Utility.CAMPO_INGREDIENT5);
            int indiceIngredient6 = cursor.getColumnIndex(Utility.CAMPO_INGREDIENT6);
            int indiceIngredient7 = cursor.getColumnIndex(Utility.CAMPO_INGREDIENT7);
            int indiceIngredient8 = cursor.getColumnIndex(Utility.CAMPO_INGREDIENT8);
            int indiceIngredient9 = cursor.getColumnIndex(Utility.CAMPO_INGREDIENT9);
            int indiceIngredient10 = cursor.getColumnIndex(Utility.CAMPO_INGREDIENT10);
            int indiceCondiment = cursor.getColumnIndex(Utility.CAMPO_CONDIMENT);


            // Obtener valores de las columnas
            int id = cursor.getInt(indiceId);
            String platename = cursor.getString(indicePlatename);
            String ingredient1 = cursor.getString(indiceIngredient1);
            String ingredient2 = cursor.getString(indiceIngredient2);
            String ingredient3 = cursor.getString(indiceIngredient3);
            String ingredient4 = cursor.getString(indiceIngredient4);
            String ingredient5 = cursor.getString(indiceIngredient5);
            String ingredient6 = cursor.getString(indiceIngredient6);
            String ingredient7 = cursor.getString(indiceIngredient7);
            String ingredient8 = cursor.getString(indiceIngredient8);
            String ingredient9 = cursor.getString(indiceIngredient9);
            String ingredient10 = cursor.getString(indiceIngredient10);
            String condiment = cursor.getString(indiceCondiment);

            // Crear la cadena de información
            platedetails = platename + "\nIngredientes: " + ingredient1 + " " + ingredient2 + " " + ingredient3 + " " + ingredient4 + " " + ingredient5 + " "
                    + ingredient6 + " " + ingredient7 + " " + ingredient8 + " " + ingredient9 + " " + ingredient10 + "\nCondimentos: " + condiment;

        }
        cursor.close();
        db.close();
        return  platedetails;
    };

    private void showDetails() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Este es tu plato");
        builder.setMessage(platedetails);

        //Boton del dialogo
        builder.setPositiveButton("Entendido!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void checkoutPlateList() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Plate plate = null;
        listaPlate = new ArrayList<Plate>();

        //La consulta se realiza mediante un objeto Cursor que ejecuta la consulta SQL:
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utility.TABLA_PLATES, null);
        while (cursor.moveToNext()) {
            plate = new Plate();
            plate.setId(cursor.getInt(0));
            plate.setPlateName(cursor.getString(1));
            plate.setIngredient1(cursor.getString(2));
            plate.setIngredient2(cursor.getString(3));
            plate.setIngredient3(cursor.getString(4));
            plate.setIngredient4(cursor.getString(5));
            plate.setIngredient5(cursor.getString(6));
            plate.setIngredient6(cursor.getString(7));
            plate.setIngredient7(cursor.getString(8));
            plate.setIngredient8(cursor.getString(9));
            plate.setIngredient9(cursor.getString(10));
            plate.setIngredient10(cursor.getString(11));
            listaPlate.add(plate);
        }
        earnList();

        //ArrayAdapter para MOSTRAR LA IN FORMACIÓN en la lista del ListView
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInfoPlate);
        platelist.setAdapter(adapter);
    }

    private void earnList() {
        listaInfoPlate = new ArrayList<String>();
        for (int i=0; i<listaPlate.size(); i++){
            listaInfoPlate.add(listaPlate.get(i).getId() + " - " + listaPlate.get(i).getPlateName());
        }
    }
}