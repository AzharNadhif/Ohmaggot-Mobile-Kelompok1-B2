package com.example.projectakhir;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectakhir.SharedPreferencesHelper;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    EditText editName, editEmail, editPassword;
    Button saveButton, backButton;

    private SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editName = findViewById(R.id.editnama);
        editEmail = findViewById(R.id.editemail);
        editPassword = findViewById(R.id.editpass);
        saveButton = findViewById(R.id.saveButton);
        backButton = findViewById(R.id.backButton);

        sharedPreferencesHelper = new SharedPreferencesHelper();

        String userId = sharedPreferencesHelper.getUserId(this);
        String authToken = sharedPreferencesHelper.getAuthToken(this);

        getUserData(userId, authToken);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = editName.getText().toString();
                String newEmail = editEmail.getText().toString();
                String newPassword = editPassword.getText().toString();

                updateUserData(userId, authToken, newName, newEmail, newPassword);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this, Profile.class);
                startActivity(intent);
            }
        });
    }

    private void getUserData(String userId, String authToken) {
        String apiUrl = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-ydypj/endpoint/getPenggunaById?id=" + userId;

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                apiUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject user = response.getJSONObject(0);

                            editName.setText(user.getString("username"));
                            editEmail.setText(user.getString("email"));
                            editPassword.setText(user.getString("password"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditProfile.this, "Error fetching user data", Toast.LENGTH_SHORT).show();
                    }
                }) {
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + authToken);
                return headers;
            }
        };

        // Menggunakan getInstance dari VolleySingleton
        Volley.newRequestQueue(this).add(request);
    }

    private void updateUserData(String userId, String authToken, String newName, String newEmail, String newPassword) {
        String apiUrl = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-ydypj/endpoint/updatePenggunaById?id=" + userId;

        StringRequest stringRequest = new StringRequest(Request.Method.PUT, apiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EditProfile.this, "Status berhasil diubah", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EditProfile.this, BerandaActivity.class);
                startActivity(intent);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditProfile.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }) {
        protected Map<String,String> getParams(){
            Map<String,String> params = new HashMap<>();
            params.put("username", newName);
            params.put("email",newEmail);
            params.put("password", newPassword);
            return params;
        }

        };

        Volley.newRequestQueue(this).add(stringRequest);


    }
}
