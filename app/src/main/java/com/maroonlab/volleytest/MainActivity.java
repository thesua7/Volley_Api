package com.maroonlab.volleytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.t_view);
        Button btn = findViewById(R.id.btn);

        TextView tv_sec = findViewById(R.id.test101);
        Button btn_2 = findViewById(R.id.button);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://karmasangsthan.com.bd/api/app_api/welcome-content?fbclid=IwAR1iMIzaPf13Rad_AfJKdvGlrH1_Sr-pHqMs4pOXOufMY199xD45o3EkELI";
                JsonObjectRequest jq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Test","ok");
                        try {


                            JSONObject json_array = response.getJSONObject("data");

                             //   JSONObject  jobj = json_array.getJSONObject(0);
                                String no_of_industries = json_array.getString("num_of_industries");
                                String no_of_employees = json_array.getString("num_of_employees");
                                String no_of_working_sectors = json_array.getString("num_of_working_sectors");
                                String no_of_occupations = json_array.getString("num_of_occupations");



                                tv.setText(no_of_industries+" "+ no_of_employees+" "+ no_of_working_sectors+" "+no_of_occupations);



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

        btn_2.setOnClickListener(new View.OnClickListener() {
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            @Override
            public void onClick(View v) {
               String url = "http://192.168.0.181/volley_test/post_test.php";
               StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       Log.d("x","Bomx");
                      tv_sec.setText(response);

                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Log.d("y","Bomy");
                       tv_sec.setText("response failed");
                   }
               }){
                   @Override
                   public Map<String,String> getParams(){

                       Map<String,String> params = new HashMap<String,String>();
                       params.put("Data_1","Value_1");
                       params.put("Data_2","Value_2");
                       params.put("Data_3","Value_3");
                       return params;

                   }
                   @Override
                   public Map<String,String> getHeaders() throws AuthFailureError{
                       Map<String,String> params = new HashMap<String,String>();
                       params.put("Content-type","application/x-www-form-urlencoded");
                       return params;
                   }

               };

               requestQueue.add(stringRequest);
            }

        });

    }
}