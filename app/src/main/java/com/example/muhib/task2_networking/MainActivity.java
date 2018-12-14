package com.example.muhib.task2_networking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.Request;
import org.json.JSONObject;
import java.util.HashMap;

import static com.example.muhib.task2_networking.NetworkPresenter.mRequestQueue;

public class MainActivity extends AppCompatActivity implements ResultInterface, View.OnClickListener {
    int Get=Request.Method.GET;
    int Post=Request.Method.POST;
    Button bt_get,bt_post;
    String post_url = "http://webdevelopmentreviews.net/carhires/webservice/lang_list";
    String get_url = "http://httpbin.org/get?param1=hello";
    HashMap<String, String> headers = new HashMap<>();
    HashMap<String, String> params = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_get=findViewById(R.id.bt_get);
        bt_post=findViewById(R.id.bt_post);

        bt_get.setOnClickListener(this);
        bt_post.setOnClickListener(this);
        params=getParams();
        headers=getheaders();


       /* CustomOkHttp okHttp=new CustomOkHttp(url,headers,params);
        okHttp.geview();
*/
    }

    private HashMap<String,String> getheaders() {
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        return headers;
    }

    private HashMap<String,String> getParams() {
        params.put("name", "Androidhive");
        params.put("email", "abc@androidhive.info");
        params.put("password", "password123");
        return params;
    }

    @Override
    public void OnSuccess(final JSONObject result, final String type) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(type.equals("lan_list"))
                {
                    Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
          }

    @Override
    public void OnFailure(final String responseMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),responseMessage,Toast.LENGTH_SHORT).show();

            }
        });
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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bt_get:
                new NetworkPresenter(this).mRequests(this,this,Get,get_url,params,headers,"lan_list");

                break;
            case R.id.bt_post:
                new NetworkPresenter(this).mRequests(this,this,Post,post_url,params,headers,"lan_list");

                break;
        }

    }
}
