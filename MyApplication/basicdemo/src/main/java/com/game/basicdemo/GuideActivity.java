package com.game.basicdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.game.basicdemo.adapter.ViewPagerAdapter;
import com.game.basicdemo.hanlder.ScreenManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/2/5.
 */

public class GuideActivity extends Activity implements View.OnClickListener ,ViewPager.OnPageChangeListener{

    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;
    private Button start_btn;

    //引导资源
    private static final int[] pics = {
      R.drawable.hzw_1,R.drawable.hzw_2,R.drawable.hzw_3,R.drawable.hzw_4,
    };

    private ImageView[] dots;

    private int currentIndex;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_guide);

        ScreenManager.getScreenManager().pushActivity(this);

        start_btn = (Button) findViewById(R.id.start_btn);
        start_btn.setVisibility(View.GONE);
        start_btn.setOnClickListener(this);

        views = new ArrayList<View>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        for(int i=0;i<pics.length;i++){
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(params);
            iv.setImageResource(pics[i]);
            views.add(iv);
        }
        vp = (ViewPager) findViewById(R.id.viewpager);
        vpAdapter = new ViewPagerAdapter(views);
        vp.setAdapter(vpAdapter);

        vp.setOnPageChangeListener(this);

        initDots();
    }

    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        dots = new ImageView[pics.length];

        for(int i = 0;i<pics.length;i++){
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(true);
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);
        }

        currentIndex = 0;
        dots[currentIndex].setEnabled(false);
    }
    /**
     *设置当前的引导页
     */
    private void setCurView(int position){
        if(position < 0 ||  position >=pics.length){
            return;
        }

        vp.setCurrentItem(position);
    }
    /**
     *这只当前引导小点的选中
     */
    private void setCurDot(int position){
        if(position < 0 || position > pics.length - 1 || currentIndex == position){
            return;
        }

        dots[position].setEnabled(false);
        dots[currentIndex].setEnabled(true);
        currentIndex = position;
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurDot(position);
        if( position ==pics.length -1){
            start_btn.setVisibility(View.VISIBLE);
        }else{
            start_btn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.start_btn){
            startActivity(new Intent(GuideActivity.this,MainActivity.class));
            finish();
        }else {
            int position = (Integer) v.getTag();
            setCurView(position);
            setCurDot(position);
        }
    }
}
