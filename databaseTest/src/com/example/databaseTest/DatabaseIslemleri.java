package com.example.databaseTest;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;

public class DatabaseIslemleri extends ListActivity implements View.OnClickListener{

    private Veritabani dbYardimcisi;
    private Cursor cursor;
    private Button ekleButonu;
    private Button sorgulaButonu;
    private Button silmeButonu;
    private Button guncellemeButonu;
    SimpleCursorAdapter kitaplar;
    int[] textViewleriDoldur = {R.id.textViewId, R.id.textViewName, R.id.textViewYear};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.liste);
        butonlariIlkle();
        dbYardimcisi = new Veritabani(this.getBaseContext());
        dbYardimcisi.veritabaninaBaglan();
        kayitlariGetir();
    }

    private void butonlariIlkle() {

        this.ekleButonu = (Button) findViewById(R.id.buttonEkle);
        this.ekleButonu.setOnClickListener(this);
        this.sorgulaButonu = (Button) findViewById(R.id.buttonSorgula);
        this.sorgulaButonu.setOnClickListener(this);
        this.guncellemeButonu = (Button) findViewById(R.id.ButtonGuncelle);
        this.guncellemeButonu.setOnClickListener(this);
        this.silmeButonu = (Button) findViewById(R.id.buttonSil);
        this.silmeButonu.setOnClickListener(this);
    }

    private void kayitlariGetir() {
        cursor = dbYardimcisi.tumKitaplariGetir();
        startManagingCursor(cursor);
        kitaplar = new SimpleCursorAdapter(this, R.layout.satir, cursor, Veritabani.KOLONLAR, textViewleriDoldur);
        setListAdapter(kitaplar);
    }

    @Override
    public void onClick(View v) {
        if (v == this.ekleButonu){
            Kitap kitap1 = new Kitap("Merhaba Android", 2009);
            Kitap kitap2 = new Kitap("Hello", 2010);
            Kitap kitap3 = new Kitap("Android", 2011);
            dbYardimcisi.kitapEkle(kitap1);
            dbYardimcisi.kitapEkle(kitap2);
            dbYardimcisi.kitapEkle(kitap3);
            cursor = dbYardimcisi.tumKitaplariGetir();
            kitaplar.changeCursor(cursor);
        }
        else if (v == this.sorgulaButonu){
            cursor = dbYardimcisi.adaGoreKitapGetir("Android");
            kitaplar.changeCursor(cursor);
        }
        else if (v == this.guncellemeButonu){

            if(cursor != null && !cursor.moveToFirst())
                return;

            while (cursor.isAfterLast() == false){
                Kitap kitap = new Kitap(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
                if(kitap.kitapAdi.equals("Android")){
                    kitap.kitapAdi = "Android Updated";
                    dbYardimcisi.kitapGuncelle(kitap);
                }
                cursor.moveToNext();
            }
            cursor.requery();
        }
        else if (v == this.silmeButonu){
            if(cursor != null && !cursor.moveToFirst())
                return;
            while(cursor.isAfterLast() == false){
                Kitap kitap = new Kitap(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
                if (kitap.kitapAdi.equals("Android Updated")){
                    dbYardimcisi.kitapSil(kitap);
                }
                cursor.moveToNext();
            }
            cursor.requery();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(dbYardimcisi != null){
            dbYardimcisi.VeritabaniniKapat();
        }
    }
}
