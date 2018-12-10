package com.example.muhib.task2_networking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.muhib.task2_networking.NetworkPresenter.mRequestQueue;

public class MainActivity extends AppCompatActivity implements ResultInterface{
    int Get=Request.Method.GET;
    int Post=Request.Method.POST;
    String url = "http://webdevelopmentreviews.net/carhires/webservice/lang_list";
    HashMap<String, String> headers = new HashMap<String, String>();
    HashMap<String, String> params = new HashMap<String, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        params=getParams();
        headers=getheaders();
        new NetworkPresenter().Requests(this,this,Post,url,params,headers,"lan_list");

    }

    private HashMap<String,String> getheaders() {
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        return headers;
    }

    private HashMap<String,String> getParams() {
        return params;
    }

    @Override
    public void OnSuccess(JSONObject result, String type) {
        if(type.equals("lan_list"))
        {
            Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnFailure(String responseMessage) {
    Toast.makeText(getApplicationContext(),responseMessage,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        try {
            mRequestQueue.cancelAll("lan_list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
