package com.example.feedbackapplication.Controller;

public class Utility {

    //Constantes campos tabla PRODUCTO
    public static final String TABLA_ITEMS = "items";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_PRODUCTO = "producto";
    public static final String CAMPO_MARCA = "marca";
    public static final String CAMPO_CT = "cantidad";

    public static final String CREAR_TABLA_ITEMS =
            "CREATE TABLE " + TABLA_ITEMS + " ("
                    + CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + CAMPO_PRODUCTO + " TEXT, "
                    + CAMPO_MARCA + " TEXT, "
                    + CAMPO_CT + " TEXT)";

    //Constantes campos tabla DESPENSA
    public static final String TABLA_PANTRY = "pantry";
    public static final String CAMPO_ID_PANTRY = "idpantry";
    public static final String CAMPO_ITEM_PANTRY = "pantryitem";
    public static final String CAMPO_ITEM_BRAND = "pantryitembrand";
    public static final String CAMPO_ITEM_QTY = "pantryitemqty";

    public static final String CREAR_TABLA_PANTRY =
            "CREATE TABLE " + TABLA_PANTRY + " ("
                    + CAMPO_ID_PANTRY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + CAMPO_ITEM_BRAND + " TEXT, "
                    + CAMPO_ITEM_QTY + " TEXT, "
                    + CAMPO_ITEM_PANTRY + " TEXT)";


    //Constantes campos tabla PLATOS
    public static final String TABLA_PLATES = "food";
    public static final String CAMPO_ID_PLATE = "id";
    public static final String CAMPO_PLATENAME = "plate";
    public static final String CAMPO_INGREDIENT1 = "ing1";
    public static final String CAMPO_INGREDIENT2 = "ing2";
    public static final String CAMPO_INGREDIENT3 = "ing3";
    public static final String CAMPO_INGREDIENT4 = "ing4";
    public static final String CAMPO_INGREDIENT5 = "ing5";
    public static final String CAMPO_INGREDIENT6 = "ing6";
    public static final String CAMPO_INGREDIENT7 = "ing7";
    public static final String CAMPO_INGREDIENT8 = "ing8";
    public static final String CAMPO_INGREDIENT9 = "ing9";
    public static final String CAMPO_INGREDIENT10 = "ing10";
    public static final String CAMPO_CONDIMENT = "condimento";
    public static final String CREAR_TABLA_PLATES =
            "CREATE TABLE " + TABLA_PLATES + " ("
                    + CAMPO_ID_PLATE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + CAMPO_PLATENAME + " TEXT, "
                    + CAMPO_INGREDIENT1 + " TEXT, "
                    + CAMPO_INGREDIENT2 + " TEXT, "
                    + CAMPO_INGREDIENT3 + " TEXT, "
                    + CAMPO_INGREDIENT4 + " TEXT, "
                    + CAMPO_INGREDIENT5 + " TEXT, "
                    + CAMPO_INGREDIENT6 + " TEXT, "
                    + CAMPO_INGREDIENT7 + " TEXT, "
                    + CAMPO_INGREDIENT8 + " TEXT, "
                    + CAMPO_INGREDIENT9 + " TEXT, "
                    + CAMPO_INGREDIENT10 + " TEXT, "
                    + CAMPO_CONDIMENT + " TEXT)";

}

