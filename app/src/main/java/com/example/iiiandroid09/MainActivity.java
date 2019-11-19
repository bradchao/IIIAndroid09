package com.example.iiiandroid09;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);
    }

    public void test1(View view) {
        new Thread(){
            public void run() {
                test11();
            }
        }.start();
    }

    private void test11(){
        try {
            URL url = new URL("http://10.0.2.2/brad01.php");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.connect();
            InputStream in = conn.getInputStream();
            InputStreamReader irs = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(irs);

            String line;
            while ( (line = br.readLine()) != null ){
                Log.v("brad", line);
            }

            br.close();

        }catch (Exception e){
            Log.v("brad", e.toString());
        }
    }

    public void test2(View view) {
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("brad", response);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("brad", "error: " + error.toString());
            }
        };
        StringRequest request = new StringRequest(Request.Method.GET,
                "https://bradchao.com/wp",
                listener,
                errorListener);
        queue.add(request);
    }
}
