package com.example.databaseTest;

/**
 * Created with IntelliJ IDEA.
 * User: said
 * Date: 8/21/13
 * Time: 9:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Kitap {

    public int id;
    public String kitapAdi;
    public int yil;

    public Kitap()
    {

    }

    public Kitap(int id, String kitapAdi, int yil){
        this.id = id;
        this.kitapAdi = kitapAdi;
        this.yil = yil;
    }

    public Kitap(String kitapAdi,int yil){
        this.kitapAdi = kitapAdi;
        this.yil = yil;
    }
}
