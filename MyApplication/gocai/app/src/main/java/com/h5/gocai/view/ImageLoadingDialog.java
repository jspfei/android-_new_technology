package com.h5.gocai.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.h5.gocai.R;

/**
 * Created by admin on 2017/4/6.
 */

public class ImageLoadingDialog extends Dialog {

    private String text;

    public ImageLoadingDialog(Context context, String text) {
        super(context, R.style.ImageloadingDialogStyle);
        this.text = text;
    }

    public ImageLoadingDialog(Context context) {
        super(context, R.style.ImageloadingDialogStyle);
    }

    private ImageLoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_imageloading);
        TextView textView = (TextView) findViewById(R.id.textViewDesc);
        textView.setText("加载中");
        if (text != null) {
            textView.setText(text);
        }
        //@android:color/transparent
    }

}