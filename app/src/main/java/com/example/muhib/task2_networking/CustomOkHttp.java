package com.example.muhib.task2_networking;

import android.util.Log;

import com.android.volley.AuthFailureError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.HttpHeaders;

public class CustomOkHttp {
  //  String url = "https://api.androidhive.info/volley/person_array.json";
    private String url = "";
    private Map<String,String> header;
    Map<String,String> paramss;
    private  final OkHttpClient client = new OkHttpClient();
    private ResultInterface resultInterface;


    public CustomOkHttp(int method, String url, Map<String,String> paramss, Map<String,String> header, ResultInterface resultInterface) {
        this.url=url;
        this.header=header;
        this.paramss=paramss;
        this.resultInterface=resultInterface;
        geview();

    }

    private void geview() {
        Headers.Builder bui=new Headers.Builder();
        if(header!=null)
        for (Map.Entry<String,String> haa:header.entrySet())
        {
            bui.add(haa.getKey(),haa.getValue());
        }
        Headers hh=bui.build();

        HttpUrl.Builder builder=HttpUrl.parse(url).newBuilder();
        if (paramss != null) {
            for(Map.Entry<String, String> param : paramss.entrySet()) {
                builder.addQueryParameter(param.getKey(),param.getValue());
            }
        }

        String urs=builder.build().toString();
        Request request=new Request.Builder().headers(hh).url(urs).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("error",e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    try {
                        resultInterface.OnSuccess(new JSONObject(response.body().string()), "");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        resultInterface.OnSuccess(new JSONObject().put("error",response.code()),"errorcode");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
     //           Log.d("tes",response.body().string());
            }
        });
    }

}
