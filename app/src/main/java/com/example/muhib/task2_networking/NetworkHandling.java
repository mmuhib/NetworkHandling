package com.example.muhib.task2_networking;

import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class NetworkHandling extends Request<JSONObject> {
    private Response.Listener<JSONObject> listener;
    private Map<String,String> params;
    VolleyInterface volleyInterface;

    private Map<String,String> headers;

    public NetworkHandling(int method, String url,Map<String,String> params,Map<String,String> headers, Response.Listener<JSONObject> reponseListener, Response.ErrorListener listener) {
        super(method, url, listener);
        this.listener=reponseListener;
        this.params=params;
        this.headers=headers;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {

            String jsonString=  new String(response.data,HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),HttpHeaderParser.parseCacheHeaders(response));
        }
        catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e))  ;
        }
            catch (JSONException e) {
             return Response.error(new ParseError(e))  ;
        }
    }



    @Override
    protected void deliverResponse(JSONObject response) {

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

    @Override
    public Priority getPriority() {
        return super.getPriority();
    }
}
