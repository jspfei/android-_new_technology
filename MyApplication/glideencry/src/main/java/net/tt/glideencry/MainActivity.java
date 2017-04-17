package net.tt.glideencry;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import net.tt.glideencry.glide.GlideSaveImage;
import net.tt.glideencry.glide.ImageShowCallBack;

public class MainActivity extends AppCompatActivity {
    private String url = "http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg";
    private ImageView imageView;
    private ImageView iv_bg_1;
    private Bitmap bitmaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.iv_bg);
        iv_bg_1 = (ImageView) findViewById(R.id.iv_bg_1);
        GlideSaveImage.with().onDownLoad1(url,this,imageView,R.mipmap.ic_launcher);
        GlideSaveImage.with().onDownLoad1(url,this,iv_bg_1,R.mipmap.ic_launcher);
    }

}
