package com.h5.h5demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;
import com.h5.h5demo.bean.FriendsZone;
import com.h5.h5demo.handler.JsSupport;
import com.h5.h5demo.utils.NetUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    static final String mimeType = "text/html";
    static final String encoding = "utf-8";
    private Activity mact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mact = this;
        init();
    }

    private void init() {

        mWebView = (WebView)findViewById(R.id.main_web_view);
        //解决点击链接跳转浏览器问题
        mWebView.setWebViewClient(new WebViewClient());

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("javascript:javacalljs()");
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("javascript:javacalljswith(" + "'http://blog.csdn.net/Leejizhou'" + ")");
            }
        });



        //assets文件路径
        String path = "http://pic55.nipic.com/file/20141209/19462408_091857535000_2.jpg";
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setDomStorageEnabled(true);
        if (NetUtils.isNetworkAvailable(mact)) { //判断是否联网
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT); //默认的缓存使用模式
        } else {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ONLY); //不从网络加载数据，只从缓存加载数据。
        }
        mWebView.requestFocus();
        //设置WebView排版算法, 实现单列显示, 不允许横向移动
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.addJavascriptInterface(MainActivity.this,"android");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器


                Map<String, String> extraHeaders = new HashMap<String, String>();
                if(view.getUrl() != null)
                    extraHeaders.put("Referer", view.getUrl());


                view.loadUrl(url,extraHeaders);

                return true;
            }
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                //这里进行无网络或错误处理，具体可以根据errorCode的值进行判断，做跟详细的处理。
                // mWebView.loadUrl("file:///android_asset/error.html");
                if(NetUtils.isNetworkAvailable(mact)){


                    mWebView.setVisibility(View.VISIBLE);
                }else{

                    mWebView.setVisibility(View.GONE);

                }
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.i("onPageStarted", url);

                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i("onPageFinished", url);
                if(NetUtils.isNetworkAvailable(mact)){
                    mWebView.setVisibility(View.VISIBLE);
                }else{
                    mWebView.setVisibility(View.GONE);
                }
                super.onPageFinished(view, url);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成
                } else {
                    // 加载中
                }
            }
        });

        localHtml();
    }
    /**
     * 显示本地网页文件
     */
    private void localHtml() {
        try {
            // 本地文件处理(如果文件名中有空格需要用+来替代)
            mWebView.loadUrl("file:///android_asset/web.html");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 显示本地图片和文字混合的Html内容
     */
    private void localHtmlImage(){
        try{
            String data = "<HTML>在模拟器 2.1 上测试,这是<IMG src=\"file:///android_asset/images/bg.jpg\"/>APK里的图片";
            mWebView.loadDataWithBaseURL(null,data,mimeType,encoding,null);
        }catch (Exception e){
            e.printStackTrace();
        }
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

    @JavascriptInterface
    public void startFunction(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,"show",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @JavascriptInterface
    public void startFunction(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(MainActivity.this).setMessage(text).show();
            }
        });

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
