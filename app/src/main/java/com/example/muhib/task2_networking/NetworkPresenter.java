package com.example.muhib.task2_networking;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.Map;
import java.util.regex.Pattern;

public class NetworkPresenter {
ResultInterface mResultInterface;
Context context;
 public  static RequestQueue mRequestQueue;
String tag;
public  NetworkHandling handling;
    public void Requests(ResultInterface mResultInterface, Context context, int Method, String url, Map<String,String> params, Map<String,String> headers,String tag)
    {
        this.mResultInterface=mResultInterface;
        this.context=context;
        this.tag=tag;
        getData(Method,url,params,headers);
    }

    private void getData(int method, String url, Map<String,String> params, Map<String,String> headers) {
        if(!url.isEmpty()) {
            mRequestQueue = Singleton.getInstance().getmRequestQueue();
            handling = new NetworkHandling(method, url, params, headers, this.success(), this.error());
            handling.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            handling.setTag(tag);
            mRequestQueue.add(handling);
        }
        else
        {
            mResultInterface.OnFailure("Please enter Correct Url");
        }

    }

    private Response.ErrorListener error() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                if(error instanceof NetworkError || error instanceof NoConnectionError)
                {
                    mResultInterface.OnFailure("Network Error");
                    Log.d("error","No Connection");//network error or no connection
                }
                else if(error instanceof ServerError )
                {
                    mResultInterface.OnFailure("Server Problem");
                    Log.d("error"," Server Problem");//server responded with a error response
                }
                else if(error instanceof TimeoutError)
                {
                    mResultInterface.OnFailure("Time out Error");
                    Log.d("error","Time out Error");//Request has timed out
                }
                else if(error instanceof AuthFailureError)
                {
                    mResultInterface.OnFailure("Authentication Error");
                    Log.d("error","Authentication Error");//Authentication Failure while performing request.
                }
                else if(error instanceof ParseError)
                {
                    mResultInterface.OnFailure("response could not be parsed");
                    Log.d("error","response could not be parsed");//server response could not be parsed
                }

          //  Log.d("error",""+error.networkResponse.statusCode);
            }
        };
    }

    private Response.Listener<JSONObject> success(){
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                mResultInterface.OnSuccess(response,tag);
            }
        };
    }

}
