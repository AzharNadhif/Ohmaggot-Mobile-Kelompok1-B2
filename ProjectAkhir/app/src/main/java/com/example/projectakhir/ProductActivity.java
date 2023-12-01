package com.example.projectakhir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    Button backHome;

    private List<ProductModel> productList;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        backHome = findViewById(R.id.backHome);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(productList, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(productAdapter);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, BerandaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        fetchDataFromMongoDB();

    }

    private void fetchDataFromMongoDB() {
        String apiUrl = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-ydypj/endpoint/getAllproduk";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                apiUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            productList.clear();

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject productJson = response.getJSONObject(i);
                                String nama_produk = productJson.getString("nama_produk");
                                String harga = productJson.getString("harga");
                                String foto = productJson.getString("foto");
                                String deskripsi = productJson.getString("deskripsi");

                                productList.add(new ProductModel(nama_produk, harga, foto, deskripsi));
                            }

                            productAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProductActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();

                    }
                });

        requestQueue.add(request);
    }
}