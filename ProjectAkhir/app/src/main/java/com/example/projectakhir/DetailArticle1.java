package com.example.projectakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailArticle1 extends AppCompatActivity {

    TextView detailDesc, detailTitle, detailTgl, detailSumber;
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_article1);

        detailTitle = findViewById(R.id.detailTitle);
        detailImage = findViewById(R.id.detailImage);
        detailTgl = findViewById(R.id.detailTgl);
        detailDesc = findViewById(R.id.detailDesc);
        detailSumber = findViewById(R.id.detailSumber);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            detailTitle.setText(bundle.getString("Title"));
            detailImage.setImageResource(bundle.getInt("Image"));
            detailTgl.setText(bundle.getString("Tanggal"));
            detailDesc.setText(bundle.getInt("Desc"));
            detailSumber.setText(bundle.getString("Sumber"));
        }
    }
}