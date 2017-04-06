package com.h5.h5demo.handler;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by admin on 2017/4/6.
 */

public class JsSupport {
    private Context mContext;
    private String json;
    public JsSupport(Context context){
        mContext = context;
    }
    public void setJson(String json){
        this.json = json;
    }
    @JavascriptInterface
    public String getJson(){
        return json;
    }
    @JavascriptInterface
    public void showToast(String str){
        Toast.makeText(mContext,str,Toast.LENGTH_SHORT).show();
    }
}
