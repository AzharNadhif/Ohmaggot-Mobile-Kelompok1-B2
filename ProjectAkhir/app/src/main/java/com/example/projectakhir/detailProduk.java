package com.example.projectakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class detailProduk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        Intent intent = getIntent();
        String nama_produk = intent.getStringExtra("nama_produk");
        String harga = intent.getStringExtra("harga");
        String foto = intent.getStringExtra("foto");
        String deskripsi = intent.getStringExtra("deskripsi");

        TextView productNameTextView = findViewById(R.id.productnamDetail);
        TextView productPriceTextView = findViewById(R.id.productpriceDetail);
        TextView productDeskriptionTextView = findViewById(R.id.deskripsiDetail);
        ImageView productImageView = findViewById(R.id.imageviewDetail);
        Button btn_buy = findViewById(R.id.btn_buy);
        Button btn_back = findViewById(R.id.backHome);

        productNameTextView.setText(nama_produk);
        productPriceTextView.setText(harga);
        productDeskriptionTextView.setText(deskripsi);

        Picasso.get().load(foto).into(productImageView);

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detailProduk.this, uploadGambar.class);
                startActivity(intent);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detailProduk.this, ProductActivity.class);
                startActivity(intent);
            }
        });


    }
}