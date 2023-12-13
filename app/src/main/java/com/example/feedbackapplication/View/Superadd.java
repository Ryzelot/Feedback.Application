package com.example.feedbackapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.feedbackapplication.Controller.ConectionHelper;
import com.example.feedbackapplication.Controller.Utility;
import com.example.feedbackapplication.R;

public class Superadd extends AppCompatActivity {

    //Declaracion de elementos del layout
    Button backbuttonsuperadd, btnsuperaddtolist;
    EditText  txtitemsuper, txtbrandsuper, txtqtysuper;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superadd);

        //Busqueda de las id del layout
        backbuttonsuperadd = findViewById(R.id.backbuttonsuperadd);
        btnsuperaddtolist = findViewById(R.id.btnsuperaddtolist);
        txtitemsuper = findViewById(R.id.txtitemsuper);
        txtbrandsuper = findViewById(R.id.txtbrandsuper);
        txtqtysuper = findViewById(R.id.txtqtysuper);
        imageView = findViewById(R.id.imageView);



        //Eventos de boton
        backbuttonsuperadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Superadd.this,MainSupermarketActivity.class);
                startActivity(intent);
            }
        });

        btnsuperaddtolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Superadd.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void addToCart() {
        //Conexion a la base de datos
        ConectionHelper conn = new ConectionHelper(this,"bd_productos",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        // EXtraccion de los campos
        String producto = txtitemsuper.getText().toString().trim();
        String marca = txtbrandsuper.getText().toString().trim();
        String cantidad = txtqtysuper.getText().toString().trim();
        ContentValues tableSupermarket = new ContentValues();

        if (producto.isEmpty() || marca.isEmpty() || cantidad.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Todos los campos deben ser completados", Toast.LENGTH_SHORT).show();
        } else {
            tableSupermarket.put(Utility.CAMPO_PRODUCTO,txtitemsuper.getText().toString());
            tableSupermarket.put(Utility.CAMPO_MARCA,txtbrandsuper.getText().toString());
            tableSupermarket.put(Utility.CAMPO_CT,txtqtysuper.getText().toString());

            //Insersion de datos en SQLite
            Long idResultante = db.insert(Utility.TABLA_ITEMS,Utility.CAMPO_ID, tableSupermarket);
        }

        /*Long guarda el id resultante
        * Valor positivo (> 0): Este valor representa el ID asignado a la fila recién insertada. En general, cuando el ID es un número positivo,
        * significa que la inserción fue exitosa y la fila se agregó a la tabla.

        Valor igual a 0: En algunos casos, un valor de 0 puede indicar que la inserción fue exitosa, pero el ID de la fila recién insertada es 0.
        *  Esto puede ocurrir si la columna de ID es un entero no autonumérico y se le asigna explícitamente el valor 0.

        Valor igual a -1: El valor -1 generalmente indica que la inserción fue fallida. Puede deberse a varios motivos, como violación de restricciones de clave única,
        * violación de restricciones de nulos, etc.
        * */

        cleaner();
        Toast.makeText(getApplicationContext(),"Item agregado a la lista" , Toast.LENGTH_SHORT).show(); //Mensaje emergente
        db.close();
    }

    private void cleaner() {
        txtitemsuper.setText("");
        txtbrandsuper.setText("");
        txtqtysuper.setText("");
    }
}