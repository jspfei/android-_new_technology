package com.jf.butterknife;

import android.content.Context;
import android.widget.Button;

import butterknife.OnClick;

/**
 * Created by admin on 2017/2/21.
 */

public class MyButton extends Button {

    public MyButton(Context context) {
        super(context);
    }
    @OnClick
    public void onClick(){

    }
}
