package com.example.projectakhir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainArticle extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    ArticleAdapter adapter;
    DataClass androidData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_article);

        recyclerView = findViewById(R.id.recycleView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainArticle.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();

        androidData = new DataClass("Budidaya Maggot Black Soldier Fly (BSF)",R.string.artikel1,"10/01/23",R.drawable.artikel1);
        dataList.add(androidData);

        androidData = new DataClass("Maggot pun Naik Kelas Jadi Sereal dan Bahan Produk Kecantikan",R.string.artikel2,"11/03/23",R.drawable.artikel2);
        dataList.add(androidData);

        androidData = new DataClass("Mahasiswa BBK UNAIR Kenalkan Budidaya Maggot sebagai Inovasi Atasi Sampah",R.string.artikel3,"25/07/23",R.drawable.artikel3);
        dataList.add(androidData);

        adapter = new ArticleAdapter(MainArticle.this,dataList);
        recyclerView.setAdapter(adapter);
    }
}
