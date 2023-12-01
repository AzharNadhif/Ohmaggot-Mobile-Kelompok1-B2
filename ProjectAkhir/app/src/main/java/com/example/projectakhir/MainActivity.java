package com.example.projectakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signupButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();

                String apiUrl = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-ydypj/endpoint/getPenggunaByPassword?username=" + enteredUsername + "&password=" + enteredPassword;

                com.android.volley.RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        apiUrl,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                try {
                                    if (response.length() > 0) {
                                        JSONObject userObject = response.getJSONObject(0);
                                        String username = userObject.getString("username");
                                        String password = userObject.getString("password");
                                        String email = userObject.getString("email");
                                        String userId = userObject.getString("_id");

                                        SharedPreferencesHelper.saveUserId(MainActivity.this, userId);
                                        SharedPreferencesHelper.saveUserData(MainActivity.this, username, password, email);

                                        Intent intent = new Intent(MainActivity.this, BerandaActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Username atau Password salah", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Terjadi Kesalahan. Silahkan coba lagi.\n" + error.toString(),Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        }
                );

                requestQueue.add(jsonArrayRequest);

            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });



    }
}