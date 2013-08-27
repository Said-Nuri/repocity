package com.example.databaseTest;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

public class Veritabani extends Activity {

    private SQLiteDatabase db;
    private final Context contex;
    private final VeritabaniYardimcisi dbYardimcisi;
    public static String KITAPID_KOLONU = "_id";
    public static String KITAPADI_KOLONU = "KitapAdi";
    public static String KITAPYILI_KOLONU = "Yil";
    public static String[] KOLONLAR = { KITAPID_KOLONU, KITAPADI_KOLONU, KITAPYILI_KOLONU };

    public Veritabani(Context c){
        contex = c;
        dbYardimcisi = new VeritabaniYardimcisi(contex);
    }

    public void VeritabaniniKapat(){
        db.close();
    }

    public void veritabaninaBaglan() throws SQLiteException{
        try{
            db = dbYardimcisi.getReadableDatabase();
        }catch (SQLiteException ex){
            db = dbYardimcisi.getReadableDatabase();
        }
    }

    public long kitapEkle(Kitap kitap){
        try{
            ContentValues yeniicerik = new ContentValues();
            yeniicerik.put(KITAPADI_KOLONU, kitap.kitapAdi);
            yeniicerik.put(KITAPYILI_KOLONU, kitap.yil);
            return db.insert(VeritabaniYardimcisi.TABLENAME, null, yeniicerik);
        }catch (SQLiteException ex){
            return -1;
        }
    }

    public Cursor adaGoreKitapGetir(String arananKelime){
        Cursor mursor = db.query( VeritabaniYardimcisi.TABLENAME, KOLONLAR, KITAPADI_KOLONU + " like " + "'%" + arananKelime + "%'", null, null, null, null);
        return mursor;
    }

    public Cursor tumKitaplariGetir(){
        return db.query(VeritabaniYardimcisi.TABLENAME, KOLONLAR, null, null, null, null, null);
    }

    public int kitapGuncelle(Kitap kitap){
        ContentValues cv = new ContentValues();
        cv.put(KITAPADI_KOLONU, kitap.kitapAdi);
        cv.put(KITAPYILI_KOLONU, kitap.yil);
        return db.update(VeritabaniYardimcisi.TABLENAME, cv, KITAPID_KOLONU + "=?", new String[]{String.valueOf(kitap.id)});
    }

    public void kitapSil(Kitap kitap){
        db.delete(VeritabaniYardimcisi.TABLENAME, KITAPID_KOLONU + "=?", new String[]{String.valueOf(kitap.id)});
    }
}
