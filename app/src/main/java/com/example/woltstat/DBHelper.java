package com.example.woltstat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper
// https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "statisztika.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "adatok";
    private static final String COL_BRUTTO = "brutto";
    private static final String COL_NETTO = "netto";
    private static final String COL_MEGTETT_KM = "megtettKM";
    private static final String COL_TANKOLAS =  "tank";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE_NAME +" (" +
                COL_BRUTTO + " INTEGER NOT NULL, " +
                COL_NETTO + " INTEGER NOT NULL, " +
                COL_MEGTETT_KM + " INTEGER NOT NULL, " +
                COL_TANKOLAS + " INTEGER NOT NULL " +
                ");";
        db.execSQL(sql);

        String addRecord = "INSERT INTO "+TABLE_NAME+" ("+
                COL_BRUTTO +"," + COL_NETTO + ", " + COL_MEGTETT_KM + ", " + COL_TANKOLAS + " )" +
                "VALUES ( 0, 0, 0, 0);";

        db.execSQL(addRecord);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    /*public boolean bruttoNettoRogzit(int brutto, int netto){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
    }*/

    public boolean rogzites(int brutto, int netto, int megtettKM, int tank){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_BRUTTO, brutto);
        values.put(COL_NETTO, netto);
        values.put(COL_MEGTETT_KM, megtettKM);
        values.put(COL_TANKOLAS, tank);
        return db.insert(TABLE_NAME, null, values) != -1;
    }

    public Cursor listaz() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, new String[]{COL_BRUTTO,COL_NETTO,COL_MEGTETT_KM,COL_TANKOLAS},
                null,null,null,null,null);
        /*
        db.rawQuery("SELECT * FROM "+TABLE_NAME + " WHERE "+COL_VEZNEV
                +" = ? AND "+COL_KERNEV+ " = ?", new String[]{"Gipsz", "Jakab"});
        */

    }
}