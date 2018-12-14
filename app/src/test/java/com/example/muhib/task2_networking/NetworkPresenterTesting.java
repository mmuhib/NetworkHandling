package com.example.muhib.task2_networking;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.google.gson.JsonObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLog;

import java.util.HashMap;

import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class NetworkPresenterTesting
{
    @Mock Context fakeContext;
    ResultInterface mResultInterface;
    NetworkPresenter mNetworkPresenter;

    @Mock
    HashMap<String, String> headers = new HashMap<>();
    @Mock
    HashMap<String, String> params = new HashMap<>();


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mNetworkPresenter=new NetworkPresenter(fakeContext);

    }

    @Test
    public void name() {


    }
    /*
    @Mock
    RequestQueue mRequestQueue;
    @Mock
    NetworkPresenter mNetworkPresenter;
    @Mock
    ResultInterface mResu;
    @Mock
    int Post=Request.Method.POST;
    @Mock
    String url = "http://webdevelopmentreviews.net/carhires/webservice/lang_list";
    @Mock
    MainActivity mainActivity;
    @Mock
    HashMap<String, String> headers = new HashMap<>();
    @Mock
    HashMap<String, String> params = new HashMap<>();
    Context mContext;
    JsonObject object;

    @Before
    public void setUp() throws Exception {
//        mainActivity=Robolectric.setupActivity(MainActivity.class);
        ShadowLog.stream = System.out;
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        mRequestQueue=Singleton.getInstance().getmRequestQueue();
        mNetworkPresenter=new NetworkPresenter();
        mNetworkPresenter.getData(Post,url,params,headers);
    }

    @Test
    public void testmethod()
    {
       // Assert.assertEquals(object,mNetworkPresenter.success());
       // Assert.assertEquals("Error",mNetworkPresenter.error());
        //System.out.println(mNetworkPresenter.success());
    }
*/
}
