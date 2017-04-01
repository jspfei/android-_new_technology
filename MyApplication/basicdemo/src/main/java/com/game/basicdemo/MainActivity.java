package com.game.basicdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


import com.game.basicdemo.adapter.MyGridViewAdapter;
import com.game.basicdemo.bean.ADInfo;
import com.game.basicdemo.bean.Picture;
import com.game.basicdemo.hanlder.ScreenManager;
import com.game.basicdemo.lib.CycleViewPager;
import com.game.basicdemo.utils.ViewFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private Button exit_screen_btn;
    private Activity act;
    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ADInfo> infos = new ArrayList<ADInfo>();
    private CycleViewPager cycleViewPager;
    private GridView class_gv;

    private String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};
    private int[] images = {R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.h4,
                            R.drawable.h5,R.drawable.h6,R.drawable.h7,R.drawable.h8};
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScreenManager.getScreenManager().pushActivity(this);
        context = this;
        exit_screen_btn = (Button) findViewById(R.id.exit_screen_btn);
        exit_screen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeAll_1();
            }
        });

        initialize();
        initGridView();
    }

    private void initGridView() {
        class_gv = (GridView) findViewById(R.id.class_gv);
        MyGridViewAdapter myGridViewAdapter = new MyGridViewAdapter(getPictureList(),context);
        class_gv.setAdapter(myGridViewAdapter);
        class_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,"pic  "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private List<Picture> getPictureList(){
        List<Picture> list = new ArrayList<Picture>();
        for(int i=0;i<images.length;i++){
            Picture picture = new Picture();
            picture.setImageId(images[i]);
            list.add(picture);
        }
        return list;
    }

    @SuppressLint("NewApi")
    private void initialize() {

        cycleViewPager = (CycleViewPager) getFragmentManager()
                .findFragmentById(R.id.fragment_cycle_viewpager_content);

        for(int i = 0; i < imageUrls.length; i ++){
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("图片-->" + i );
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(this, infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(this, infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(this, infos.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);
        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(2000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {
            if (cycleViewPager.isCycle()) {
                position = position - 1;
                Toast.makeText(MainActivity.this,
                        "position-->" + info.getContent(), Toast.LENGTH_SHORT)
                        .show();
            }

        }

    };
    private void closeAll_1(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,ExitActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
