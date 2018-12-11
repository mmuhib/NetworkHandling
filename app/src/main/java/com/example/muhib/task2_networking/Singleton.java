package com.example.muhib.task2_networking;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Singleton {
    public static Singleton instance=null;
    private RequestQueue mRequestQueue;

    public Singleton() {
        //Constructor
        mRequestQueue=Volley.newRequestQueue(MyApplication.getAppContext());
    }
    public static Singleton getInstance()
    {
        if(instance==null)
        {
            instance=new Singleton();
        }
        return instance;
    }
    public RequestQueue getmRequestQueue()
    {
        return mRequestQueue;
    }
}
