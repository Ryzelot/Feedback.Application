package com.example.feedbackapplication.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConectionHelper extends SQLiteOpenHelper {

    public ConectionHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utility.CREAR_TABLA_ITEMS); // Crea tabla items
        db.execSQL(Utility.CREAR_TABLA_PANTRY); // Crea tabla despensa
        db.execSQL(Utility.CREAR_TABLA_PLATES); // Crea tabla platos

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Utility.TABLA_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + Utility.TABLA_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + Utility.TABLA_PLATES);
        onCreate(db);
    }
}
