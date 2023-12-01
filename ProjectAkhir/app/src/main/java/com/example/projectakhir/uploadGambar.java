package com.example.projectakhir;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class uploadGambar extends AppCompatActivity {

    Button btnEncode, btnDecode;
    TextView textView;
    ImageView imageView;
    String sImage;

    private final ActivityResultLauncher<Intent> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                                handleImageSelection(result.getData());
                        }

                        }
                    });


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_gambar);

        btnEncode = findViewById(R.id.btn_Encode);

        btnEncode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(uploadGambar.this
//                        , Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(uploadGambar.this
//                        ,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
//                        , 100);
//                }else {

                    pickImageLauncher.launch(new Intent(Intent.ACTION_PICK).setType("image/*"));

                }
            });
//        });

//        btnDecode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 byte[] bytes = Base64.decode(sImage,Base64.DEFAULT);
//                 Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0
//                 ,bytes.length);
//                 imageView.setImageBitmap(bitmap);
//            }
//        });
    }

        private void handleImageSelection(@Nullable Intent data) {
        if (data!= null) {
            Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                    byte[] bytes = stream.toByteArray();
                    sImage = Base64.encodeToString(bytes, Base64.DEFAULT);
//                    textView.setText(sImage);

                    uplodPhotoToMongoDB(sImage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    private void uplodPhotoToMongoDB(String base64Photo) {
        String apiUrl = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-ydypj/endpoint/uploadGambar";

        JSONObject requestData = new JSONObject();
        try {
            requestData.put("transaksi", base64Photo);
//            requestData.put("transaksi", transaksi);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                apiUrl,
                requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        try {
//                            if (response != null) {
//
////                                String transaksiResult = response.getString("transaksi");

//                                System.out.println(response.toString());


                                Toast.makeText(uploadGambar.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(uploadGambar.this, BerandaActivity.class);
                                startActivity(intent);
//                            } else {
//                                Toast.makeText(uploadGambar.this, "Server responnse is not valid or empty", Toast.LENGTH_SHORT).show();
//                            }
//
//                        } catch (JSONException e){
//                            e.printStackTrace();
//                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(uploadGambar.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(uploadGambar.this, BerandaActivity.class);
                        startActivity(intent);



//                        if (error.networkResponse != null) {
//                            Log.e("Volley Error", "HTTP Status Code: " + error.networkResponse.statusCode);
//                            if (error.networkResponse.data != null) {
//                                String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
//                                Log.e("Volley Error", "Response body: " + responseBody);
//                            }
//                        }
//                        Toast.makeText(uploadGambar.this, "Error uploadinng photo", Toast.LENGTH_SHORT).show();
//                        error.printStackTrace();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}




