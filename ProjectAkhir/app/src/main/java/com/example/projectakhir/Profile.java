package com.example.projectakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity {

    private TextView textUsername;
    private TextView textEmail;
    private Button btnEditProfile, btnBackHome, btnLogout;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textUsername = findViewById(R.id.textUsername);
        textEmail = findViewById(R.id.textEmail);
        btnEditProfile = findViewById(R.id.editButton);
        btnBackHome = findViewById(R.id.backHome);
        btnLogout = findViewById(R.id.logout);


        String userId = SharedPreferencesHelper.getUserId(this);

        fetchDataFromMongoDB(userId);

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, BerandaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        if (userId != null && !userId.isEmpty()) {
//            Toast.makeText(this, "User ID: " + userId, Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "User ID tidak tersedia", Toast.LENGTH_SHORT).show();
//        }

//
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, EditProfile.class);
                startActivity(intent);
            }
        });



    }

    private void fetchDataFromMongoDB(String userId) {
        String  apiUrl = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-ydypj/endpoint/getPenggunaById?id=" + userId;

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest userDataRequest = new JsonArrayRequest(
                Request.Method.GET,
                apiUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            if (response != null && response.length() > 0) {

                                JSONObject userObject = response.getJSONObject(0);

                                String username = userObject.getString("username");
                                String email = userObject.getString("email");

                                String usernameText = "Username: " + username;
                                String emailText = "Email: " + email;

                                textUsername.setText(usernameText);
                                textEmail.setText(emailText);


                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        requestQueue.add(userDataRequest);
    }
}