package com.game.grideimageencry;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * gride 图片加密
 * */
public class MainActivity extends AppCompatActivity {
    private Context context;
    private ImageView iv_test;
    private String url = "http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_test = (ImageView) findViewById(R.id.iv_test);
        getImageView();
    }

    private void getImageView(){
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .diskCacheStrategy( DiskCacheStrategy.NONE )
                .skipMemoryCache( true )
                .placeholder(R.mipmap.ic_launcher)
                .crossFade().into(iv_test);
    }
}
