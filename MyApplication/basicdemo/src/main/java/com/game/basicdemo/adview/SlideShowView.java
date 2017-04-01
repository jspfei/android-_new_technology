package com.game.basicdemo.adview;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.game.basicdemo.R;
import com.game.basicdemo.bean.ADInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * ViewPager实现的轮播图广告自定义视图，如京东首页的广告轮播图效果；
 * 既支持自动轮播页面也支持手势滑动切换页面
 * 
 *
 */
public class SlideShowView extends FrameLayout {
	
	private static final String TAG = "SlideShowView";
	// 使用universal-image-loader插件读取网络图片，需要工程导入universal-image-loader-1.8.6-with-sources.jar

    //自动轮播启用开关
    //private final static boolean isAutoPlay = true;
    
    //自定义轮播图的资源
    //放轮播图片的ImageView 的list
    private List<ADInfo> vediolist;
    
    private List<ImageView> imageViewsList;
    //放圆点的View的list
    private List<View> dotViewsList;
    
    private ViewPager viewPager;
    //当前轮播页
    private int currentItem  = 0;
    //定时任务
    private ScheduledExecutorService scheduledExecutorService;
    
    private Context context;
    private Context mContext;
    
    //Handler
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            viewPager.setCurrentItem(currentItem);
        }
        
    };
    
    public SlideShowView(Context context , Activity activity) {
        this(context,activity,null);
    }
    public SlideShowView(Context context, Activity activity, AttributeSet attrs) {
        this(context,activity, attrs, 0);

    }
    public SlideShowView(Context context, Activity activity, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        mContext = activity;

       initData();
    }
    /**
     * 开始轮播图切换
     */
    private void startPlay(){
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
    }
    /**
     * 停止轮播图切换
     */
    private void stopPlay(){
        scheduledExecutorService.shutdown();
    }
    /**
     * 初始化相关Data
     */
    public void initData(){
        imageViewsList = new ArrayList<ImageView>();
        dotViewsList = new ArrayList<View>();

        // 异步任务获取图片
       loadPicFromNet();
    }

    private ImageView imageViewVip;
    private TextView textViewBannerTitle;
    /**
     * 初始化Views等UI
     */
    private void initUI(Context context){
    	if(vediolist == null || vediolist.size() == 0)
    		return;
    	
        LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this, true);
        
        LinearLayout dotLayout = (LinearLayout)findViewById(R.id.dotLayout);
        dotLayout.removeAllViews();

        imageViewVip = (ImageView) findViewById(R.id.imageViewVip);

        textViewBannerTitle = (TextView)findViewById(R.id.textViewBannerTitle);

        // 热点个数与图片特殊相等
        for (int i = 0; i < vediolist.size(); i++) {
        	ADInfo item = vediolist.get(i);

        	ImageView view =  new ImageView(context);
        	//view.setTag(item.getPic());
        	if(i==0)//给一个默认图
        		view.setBackgroundResource(R.drawable.icon_empty);
        	view.setScaleType(ScaleType.FIT_XY);
        	imageViewsList.add(view);
        	
        	ImageView dotView =  new ImageView(context);
        	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	params.leftMargin = 4;
			params.rightMargin = 4;
			dotLayout.addView(dotView, params);
        	dotViewsList.add(dotView);

		}
        
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setFocusable(true);
        
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }
    
    /**
     * 填充ViewPager的页面适配器
     * 
     */
    private class MyPagerAdapter  extends PagerAdapter {

        @Override
        public void destroyItem(View container, int position, Object object) {

            //((ViewPag.er)container).removeView((View)object);
            ((ViewPager)container).removeView(imageViewsList.get(position));
        }

        @Override
        public Object instantiateItem(View container, final int position) {
        	ImageView imageView = imageViewsList.get(position);

            ADInfo item = vediolist.get(position);
            String url = item.getUrl();
            //支持gif图片
             Glide.with(mContext).load(url).centerCrop().placeholder(R.drawable.icon_empty).crossFade().into(imageView);

             // String url = "http://ww1.sinaimg.cn/mw690/805fa900jw1f5q2lmksttg20c8058qv5.gif";
           // ImageCacheSingleton.get(url, imageView);

            imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {


                  /*  if(item.isVip()){
                        boolean isUserVip = UserHandler.isVip(context);
                        if(!isUserVip){
                            PayDialog dialog = new PayDialog(context, Constant.UmengEvent.EVENT_CLICK_VIDEO_IN_BANNER);
                            dialog.setCanceledOnTouchOutside(false);
                            dialog.show();
                            return;
                        }
                    }*/

				}
			});

            ((ViewPager)container).addView(imageViewsList.get(position));

            return imageViewsList.get(position);
        }

        @Override
        public int getCount() {

            return imageViewsList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {

            return arg0 == arg1;
        }
        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {


        }

        @Override
        public Parcelable saveState() {

            return null;
        }

        @Override
        public void startUpdate(View arg0) {


        }

        @Override
        public void finishUpdate(View arg0) {

            
        }
        
    }
    /**
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     * 
     */
    private class MyPageChangeListener implements OnPageChangeListener {

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {

            switch (arg0) {
            case 1:// 手势滑动，空闲中
                isAutoPlay = false;
                break;
            case 2:// 界面切换中
                isAutoPlay = true;
                break;
            case 0:// 滑动结束，即切换完毕或者加载完毕
                // 当前为最后一张，此时从右向左滑，则切换到第一张
                if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                    viewPager.setCurrentItem(0);
                }
                // 当前为第一张，此时从左向右滑，则切换到最后一张
                else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                    viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                }
                break;
        }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

            
        }

        @Override
        public void onPageSelected(int pos) {
            
            currentItem = pos;
            for(int i=0;i < dotViewsList.size();i++){
                if(i == pos){
                    ((View)dotViewsList.get(pos)).setBackgroundResource(R.drawable.dot_focus);
                }else {
                    ((View)dotViewsList.get(i)).setBackgroundResource(R.drawable.dot_blur);
                }
            }
            ADInfo item = vediolist.get(currentItem);
            textViewBannerTitle.setText(item.getContent());

          /*  if(item.isVip()){
                imageViewVip.setVisibility(View.VISIBLE);
            }else{
                imageViewVip.setVisibility(View.GONE);
            }*/
            //不显示vip标志
            imageViewVip.setVisibility(View.GONE);
        }
        
    }
    
    /**
     *执行轮播图切换任务
     *
     */
    private class SlideShowTask implements Runnable {

        @Override
        public void run() {

            synchronized (viewPager) {
                currentItem = (currentItem+1)%imageViewsList.size();
                handler.obtainMessage().sendToTarget();
            }
        }
        
    }
    public void setData(List<ADInfo> list){
        vediolist = list;
        initData();
        initUI(context);
        startPlay();
    }
    private void loadPicFromNet(){
       if(vediolist != null && vediolist.size() > 0) return;
    /*	HttpRequest request = HttpRequestCreator.CreateHttpRequest(context,NetConstant.SUFFIX_URL , new SimpleHttpListener() {

			@Override
			public void onSuccess(JSONObject jsonObject) {
				try {
                    Log.d("SlideShowView","------------------------");
                    String str = jsonObject.getString(NetConstant.RESPONSE_KEY_VEDIOINFO);
                    Log.d("SlideShowView",str);
					vediolist = JSON.parseArray(str, VideoObj.class);
                    Log.d("SlideShowView","lenght         "+vediolist.size());
					initUI(context);
					startPlay();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailed(HttpRequestError error) {

			}
		});
        request.addParam(NetConstant.PARAM_KEY_CMD, NetConstant.PARAM_VALUE_BANNER_LIST);
        request.addParam(NetConstant.PARAM_KEY_PLATFORM, NetConstant.PLATFORM);
		request.start();*/
    }
	
}