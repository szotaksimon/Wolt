package com.example.woltstat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    public void updateBruttoNetto(int brutto, int netto){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor frissitesreVar = this.listaz();
        frissitesreVar.moveToFirst();
        Log.d("Cursor size: ", String.valueOf(frissitesreVar.getCount()));

        int bruttoGet = frissitesreVar.getInt(0);
        Log.d("Lekérdezett bruttó: ", String.valueOf(bruttoGet));
        Log.d("Beadott bruttó: ", String.valueOf(brutto));

        int nettoGet = frissitesreVar.getInt(1);
        Log.d("Lekérdezett netto: ", String.valueOf(nettoGet));
        Log.d("Beadott netto: ", String.valueOf(netto));

        int frissitettBrutto = brutto + bruttoGet;
        Log.d("Módosított bruttó: ", String.valueOf(frissitettBrutto));
        int frissitettNetto = netto + nettoGet;
        Log.d("Módosított netto: ", String.valueOf(frissitettNetto));

        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_BRUTTO+ " = " + frissitettBrutto);
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_NETTO+ " = " + frissitettNetto);

    }

    public void updateKmTank(int megtettKM, int tank){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor frissitesreVar = this.listaz();
        Log.d("Cursor size: ", String.valueOf(frissitesreVar.getCount()));
        frissitesreVar.moveToFirst();

        int megtettKmGet = frissitesreVar.getInt(2);
        Log.d("Lekérdezett megtett km: ", String.valueOf(megtettKmGet));
        int tankGet = frissitesreVar.getInt(3);
        Log.d("Lekérdezett tank: ", String.valueOf(tankGet));

        int frissitettMegtettKm = megtettKM + megtettKmGet;
        Log.d("Módosított megtett km: ", String.valueOf(frissitettMegtettKm));
        int frissitettTank = tank + tankGet;
        Log.d("Módosított tank: ", String.valueOf(frissitettTank));


        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_MEGTETT_KM+ " = " + frissitettMegtettKm);
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COL_TANKOLAS+ " = " + frissitettTank);
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