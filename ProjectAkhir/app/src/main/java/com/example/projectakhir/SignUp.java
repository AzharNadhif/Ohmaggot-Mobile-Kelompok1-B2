package com.example.projectakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends AppCompatActivity {
    EditText usernameSign;
    EditText passSign;
    EditText emailSign;
    Button signButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameSign = findViewById(R.id.userSignup);
        passSign = findViewById(R.id.passSignup);
        emailSign = findViewById(R.id.emailSignup);
        signButton = findViewById(R.id.signupButton);

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSignup();
            }
        });
    }

    private void performSignup() {
        String username = usernameSign.getText().toString().trim();
        String password = passSign.getText().toString().trim();
        String email = emailSign.getText().toString().trim();

        sendSignupRequest(username, password, email);
    }

    private void sendSignupRequest(String username, String password, String email) {
        String baseUrl = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-ydypj/endpoint/insertPengguna";

        String apiUrl = baseUrl + "?username=" + Uri.encode(username) + "&password=" + Uri.encode(password) + "&email=" + Uri.encode(email);

//        JSONObject requestBody =  new JSONObject();
//        try {
//            requestBody.put("username",username);
//            requestBody.put("password",password);
//            requestBody.put("email",email);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest signupRequest = new StringRequest(
                Request.Method.POST,
                apiUrl,
//                requestBody,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            if (jsonResponse != null && jsonResponse.length() > 0) {

                                String username = jsonResponse.getString("username");
                                String password = jsonResponse.getString("password");
                                String email = jsonResponse.getString("email");
//                                String userId = jsonResponse.getString("_id");

                                String userId = jsonResponse.has("_id") ? jsonResponse.getString("_id") : "";

                                SharedPreferencesHelper.saveUserId(SignUp.this, userId);
                                SharedPreferencesHelper.saveUserData(SignUp.this, username, password, email);

                                Intent intent = new Intent(SignUp.this, BerandaActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(SignUp.this, "Respon server tidak valid atau kosong", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SignUp.this, "Terjadi kesalahan saat memproses respons", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUp.this, "Gagal melakukan pendaftaran. Silahkan coba lagi", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }
        );

        requestQueue.add(signupRequest);
    }
}