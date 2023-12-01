package com.example.projectakhir;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductModel{
    private String nama_produk;
    private String harga;
    private String foto;
    private String deskripsi;
//    private String productImage;

    public ProductModel(String nama_produk, String harga, String foto, String deskripsi ) {
        this.nama_produk = nama_produk;
        this.harga = harga;
        this.foto = foto;
        this.deskripsi = deskripsi;

    }

    public String getNama_produk() {
        return nama_produk;
    }

    public String getHarga() {

        return harga;
    }

    public String getFoto() {
        return foto;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

}

