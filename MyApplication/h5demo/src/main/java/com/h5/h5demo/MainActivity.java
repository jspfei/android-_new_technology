package com.h5.h5demo;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.h5.h5demo.bean.FriendsZone;
import com.h5.h5demo.handler.JsSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mWebView = (WebView)findViewById(R.id.main_web_view);
        //解决点击链接跳转浏览器问题
        mWebView.setWebViewClient(new WebViewClient());
        //js支持
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        //允许访问assets 目录
        settings.setAllowFileAccess(true);
        //设置WebView排版算法, 实现单列显示, 不允许横向移动
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //assets文件路径
        String path = "http://m.51gocai.com";
        //添加json
        addJson();
        //加载html页面
        mWebView.loadUrl(path);
    }

    private void addJson() {
        JsSupport jsSupport = new JsSupport(this);
        List<FriendsZone> zones = new ArrayList<>();
        for(int i = 0;i<20;i++){
            zones.add(new FriendsZone("露露"+1,"images/icon.jpg","这是HTML测试数据"));
        }
        Gson gson = new Gson();
        String json = gson.toJson(zones);
        Log.i("HACK-TAG","json   =  "+json);
        jsSupport.setJson(json);

        mWebView.addJavascriptInterface(jsSupport,"weichat");
    }


    @Override
    public void onBackPressed(){
        if(mWebView.canGoBack()){
            mWebView.goBack();
        }else{
            super.onBackPressed();
        }
    }
}
