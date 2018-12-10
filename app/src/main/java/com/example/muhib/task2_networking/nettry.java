package com.example.muhib.task2_networking;

import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class nettry extends Request<Object> {
    private Response.Listener<Object> listener;
    private Map<String,String> params;

    private Map<String,String> headers;

    public nettry(int method, String url,Map<String,String> params,Map<String,String> headers, Response.Listener<Object> reponseListener, Response.ErrorListener listener) {
        super(method, url, listener);
        this.listener=reponseListener;
        this.params=params;
        this.headers=headers;

    }

    @Override
    protected Response<Object> parseNetworkResponse(NetworkResponse response) {

                 Log.d("tag", String.valueOf(response.data.length));

        /*catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e))  ;
        }
        catch (JSONException e) {
            return Response.error(new ParseError(e))  ;
        }*/
    return null;}

    @Override
    protected void deliverResponse(Object response) {
        listener.onResponse(response);
    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers;
    }
}
