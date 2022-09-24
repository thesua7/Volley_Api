package com.maroonlab.volleytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.tview);
        Button btn = findViewById(R.id.btn);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://run.mocky.io/v3/6411c1e3-ea2b-4d13-9fac-ae2021362dfe";
                JsonObjectRequest jq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Test","ok");
                        try {
                            JSONArray json_array = response.getJSONArray("data");

                            int i=0;
                            for (i=0;i<json_array.length();i++){
                                JSONObject jsonObject = json_array.getJSONObject(i);
                                String name = jsonObject.getString("Name");
                                String username = jsonObject.getString("Image");
                                String email = jsonObject.getString("Category");
                                Log.d("Test",name);


                                tv.setText(name+" "+ username+" "+ email);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(jq);
            }
        });
    }
}