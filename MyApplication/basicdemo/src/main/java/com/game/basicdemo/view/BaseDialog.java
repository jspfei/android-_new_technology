package com.game.basicdemo.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.game.basicdemo.R;

/**
 * Created by admin on 2017/5/23.
 */

public abstract class BaseDialog extends Dialog {
    private Context context;
    private String title;
    private OnItemCheckListener onItemCheckListener;
    protected View view;

     @Override
     protected void onCreate(Bundle bundle){
         super.onCreate(bundle);
         init();
     }

    protected void init() {
//以view来引入布局
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_broadcast, null);
        this.view=view;
        setContentView(view);
        //设置dialog大小
        Window dialogWindow = getWindow();
        WindowManager manager = ((Activity) context).getWindowManager();
        WindowManager.LayoutParams params = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.CENTER);
        Display d = manager.getDefaultDisplay(); // 获取屏幕宽、高度
        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65，根据实际情况调整
        dialogWindow.setAttributes(params);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOnItemCheckListener(OnItemCheckListener onItemCheckListener) {
        if(onItemCheckListener !=null)
             this.onItemCheckListener = onItemCheckListener;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public BaseDialog(Context context) {
        super(context);
        this.context = context;
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    public abstract int getLayoutId();
    public interface OnItemCheckListener{
        void onItemCheck(int checkedId);
    }
}
