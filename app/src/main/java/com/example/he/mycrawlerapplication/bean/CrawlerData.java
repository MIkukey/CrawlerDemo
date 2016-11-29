package com.example.he.mycrawlerapplication.bean;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.he.mycrawlerapplication.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.w3c.dom.Document;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by HE on 2016/11/25.
 */

public class CrawlerData {
    public interface Callback{
        void onClick(Object object);
    }
    Context context;
    Callback callback;
    public void getInfo(Callback callback){
        this.callback=callback;
        getData();
    }
    public CrawlerData(Context context) {
        this.context = context;
    }
    public void getData(){
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        //参数一：网址 参数二：回调监听
        StringRequest stringRequest=new StringRequest("http://news.qq.com/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onClick(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("CrawlerData", error.toString());
            }
        });
        requestQueue.add(stringRequest);


    }
    public void getImg(View view, String imgUrl){
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        ImageLoader imageLoader=new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        ImageLoader.ImageListener imageListener=ImageLoader.getImageListener((ImageView) view,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        imageLoader.get(imgUrl,imageListener);
    }


    public void jsoupData(String string){
        org.jsoup.nodes.Document document= (org.jsoup.nodes.Document) Jsoup.parse(string);
        Element element=document.getElementById("title");
        Log.e("CrawlerData", document.getAllElements().toString());

    }
}
