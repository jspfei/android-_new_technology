package net.tt.slidingmenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by admin on 2017/4/24.
 */

public class SlideMenu extends ViewGroup {
    private int mMostRecentX;
    private  final  int MENU_VIEW = 1;
    private final int MAIN_VIEW  = 2;
    private int currentView  = MAIN_VIEW;
    private Scroller scroller;
    private int mTouchSlop;

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec ,int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        initView(widthMeasureSpec,heightMeasureSpec);
    }

    private void initView(int widthMeasureSpec, int heightMeasureSpec) {
        View menuView = this.getChildAt(0);

        menuView.measure(menuView.getLayoutParams().width,heightMeasureSpec);

        View mainView = this.getChildAt(1);
        mainView.measure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View menuView  = this.getChildAt(0);
        menuView.layout(-menuView.getMeasuredWidth(),0,0,b);

        View mainView = this.getChildAt(1);
        mainView.layout(1,t,r,b);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mMostRecentX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int currentX = (int)event.getX();
                int delta = mMostRecentX - currentX;
                int scrollX = getScrollX()+delta;
                if(scrollX< -this.getChildAt(0).getWidth()){
                    scrollTo(-this.getChildAt(0).getWidth(),0);
                }else if(scrollX>0) {
                    scrollTo(0, 0);
                }else{
                    scrollBy(delta,0);
                }
                mMostRecentX = currentX;
                break;
            case MotionEvent.ACTION_UP:
                int menuCenter = -this.getChildAt(0).getWidth() /2;
                int _x = getScrollX();
                if(_x<menuCenter){
                    currentView = MENU_VIEW;
                }else{
                    currentView = MAIN_VIEW;
                }
                switchView();
                break;
            default:
                break;
        }
        return true;
    }
    @Override
    public void computeScroll(){
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),0);
            invalidate();
        }
    }
    private void switchView() {
        int startX = getScrollX();
        int dx = 0;
        if(currentView == MAIN_VIEW){
            dx = 0 - startX;
        }else if(currentView == MENU_VIEW){
            dx = -getChildAt(0).getWidth() -startX;
        }

        // 开始模拟数据
        scroller.startScroll(startX, 0, dx, 0, Math.abs(dx) * 5);
        invalidate(); // 刷新界面,会触发ViewGroup下drawChild-->child.draw-->computeScroll
    }

    /**
     * 事件分发机制，处理菜单向左滑动时，时间消费事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mMostRecentX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getX();
                int diff = moveX - mMostRecentX;
                if (Math.abs(diff) > mTouchSlop)
                    return true; // 认为是横向移动，消耗掉此事件
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 判断菜单是否显示
     *
     * @return
     */
    public boolean isShowMenu() {
        return currentView == MENU_VIEW;
    }

    /**
     * 隐藏菜单
     */
    public void hideMenu() {
        currentView = MAIN_VIEW;
        switchView();
    }

    /**
     * 显示菜单
     */
    public void showMenu() {
        currentView = MENU_VIEW;
        switchView();
    }


}
