package com.example.muhib.task2_networking;

import android.content.Context;
import android.util.Log;
import android.webkit.URLUtil;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;


public class NetworkPresenter {
    ResultInterface mResultInterface;
    Context context;
    public    static RequestQueue mRequestQueue;
    String tag;
    private   NetworkHandling handling;

    public NetworkPresenter(Context context) {
        this.context = context;
    }

    public void mRequests(ResultInterface mResultInterface, Context context, int Method, String url, Map<String,String> params, Map<String,String> headers,String tag)
    {
        this.mResultInterface=mResultInterface;
        this.context=context;
        this.tag=tag;
        getData(Method,url,params,headers);
    }

    private void getData(int method, String url, Map<String,String> params, Map<String,String> headers) {
        if(!url.isEmpty()) {
            if(URLUtil.isValidUrl(url)) {
                mRequestQueue = Singleton.getInstance().getmRequestQueue();
                handling = new NetworkHandling(method, url, params, headers, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mResultInterface.OnSuccess(response,tag);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error.networkResponse!=null)
                        {
                            handleError(error.networkResponse.statusCode);
                        }
                        else {
                            onErrorResponses(error);
                        }
                    }
                });
                handling.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                handling.setTag(tag);
                mRequestQueue.add(handling);

               /* new CustomOkHttp(method, url, params, headers, new ResultInterface(){
                    @Override
                    public void OnSuccess(JSONObject result, String type) {
                        if(type.equals("errorcode"))
                        {
                            try {
                                int error= (int) result.get("error");
                                handleError(error);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            mResultInterface.OnSuccess(result,tag);
                        }
                    }

                    @Override
                    public void OnFailure(String responseMessage) {
                     mResultInterface.OnFailure(responseMessage);
                    }
                }

                );*/
            }
            else
            {
                mResultInterface.OnFailure("Url is not Valid");
            }
        }
        else
        {
            mResultInterface.OnFailure("Please enter Url");
        }

    }


    public void onErrorResponses(VolleyError error)
    {

        if(error instanceof NetworkError || error instanceof NoConnectionError)
        {
            mResultInterface.OnFailure("Network Error");
            Log.d("error","No Connection"+error.networkResponse.statusCode);//network error or no connection
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

    }
    private void handleError(int ErrorCode)
    {
        switch (ErrorCode)
        {
            case 400:
                mResultInterface.OnFailure("    The server cannot or will not process the request due to an apparent client error ");
                break;
            case 403:
                mResultInterface.OnFailure("user might not have the necessary permissions");
                break;
            case 404:
                mResultInterface.OnFailure("The requested resource could not be found but may be available in the future. ");
                break;
            case 408:
                mResultInterface.OnFailure("The server timed out");
                break;
            case 502:
                mResultInterface.OnFailure("Network Error");
                break;
        }

    }


}
