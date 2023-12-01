package com.example.projectakhir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.projectakhir.databinding.ActivityBerandaBinding;

import java.util.ArrayList;

public class BerandaActivity extends AppCompatActivity {
    TextView sapa;
    Button iMaggot, home, profile, produk;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        sapa = findViewById(R.id.greetingTextView);
        iMaggot = findViewById(R.id.imaggot);
        home = findViewById(R.id.home);
        profile  = findViewById(R.id.profile);
        produk = findViewById(R.id.produk);


        SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper();
        String username = sharedPreferencesHelper.getUserName(this);

        String greeting = "Hello,\n" + username + "!";

        sapa.setText(greeting);

        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.slider1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.slider3, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, Profile.class);
                startActivity(intent);
            }
        });

        iMaggot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, MainArticle.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BerandaActivity.this, BerandaActivity.class);
                startActivity(intent);
            }
        });

//        ArrayList<ProductModel> productList = ProductDataHolder.getProductList();
//        if (productList != null && !productList.isEmpty()) {
//            showProductsInView(productList);
//        }
        
    }

//    private void showProductsInView(ArrayList<ProductModel> productList) {
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//
//        ProductAdapter productAdapter = new ProductAdapter(productList,this);
//        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(productAdapter);


    }

