package com.example.vostro.ev3_curp.DataBase;


import android.provider.BaseColumns;

public final class UsuarioContract
{
    /**
     * Es aqui donde se declaran cada una de las variables o nombres de los campos que tendrá la tabla
     * Con el objetivo de solamente poder llamar la variable sin necesidad de tocar los nombres que se le
     * hayan asignado.
     * **/
    public static class CurpUsuarioEntry implements BaseColumns
    {
        public static String TABLE_NAME = "usuario";
        public static final String CAMPO_NAMES = "names";
        public static final String CAMPO_FIRSTLASTNAME = "firstlastname";
        public static final String CAMPO_SECONDLASTNAME = "secondlastname";
        public static final String CAMPO_GENDER = "gender";
        public static final String CAMPO_MONTH = "month";
        public static final String CAMPO_DAY = "day";
        public static final String CAMPO_YEAR = "year";
        public static final String CAMPO_FEDERAL_ENTITY = "federal_entity";
        public static final String CAMPO_URL = "url";

    }

    /**
     * Creacion de la tabla utilizando CurpUsuarioEntry para poder mandar a llamar cada uno de los datos especificados y
     * posteriormente definirles el tipo de dato al cual pertenecen.
     * **/
    public static final String SQL_CREATE_ENTRIES =

            "CREATE TABLE " + CurpUsuarioEntry.TABLE_NAME  + " (" +
                    CurpUsuarioEntry._ID + " INTEGER PRIMARY KEY," +
                    CurpUsuarioEntry.CAMPO_NAMES + " TEXT," +
                    CurpUsuarioEntry.CAMPO_FIRSTLASTNAME + " TEXT," +
                    CurpUsuarioEntry.CAMPO_SECONDLASTNAME + " TEXT," +
                    CurpUsuarioEntry.CAMPO_GENDER + " TEXT," +
                    CurpUsuarioEntry.CAMPO_MONTH + " INTEGER," +
                    CurpUsuarioEntry.CAMPO_DAY + " INTEGER," +
                    CurpUsuarioEntry.CAMPO_YEAR +" INTEGER," +
                    CurpUsuarioEntry.CAMPO_FEDERAL_ENTITY + " TEXT," +
                    CurpUsuarioEntry.CAMPO_URL + " TEXT)";



    public  static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CurpUsuarioEntry.TABLE_NAME;
}

