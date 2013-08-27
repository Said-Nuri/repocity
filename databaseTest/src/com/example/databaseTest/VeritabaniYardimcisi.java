package com.example.databaseTest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: said
 * Date: 8/19/13
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class VeritabaniYardimcisi extends SQLiteOpenHelper {

    private static final String VERITABANI_ADI = "kıtaplar";
    private static final int VERITABANI_VERSIONU = 1;

    public static final String ID = "_id";
    public static final String KITAPADI = "KitapAdi";
    public static final String YIL = "Yil";
    public static final String TABLENAME = "KITAPLAR";

    public VeritabaniYardimcisi(Context con){
        super(con, VERITABANI_ADI, null, VERITABANI_VERSIONU);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLENAME +" (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KITAPADI + " TEXT NOT NULL," + YIL + " INTEGER NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLENAME);
        onCreate(db);
    }
}
