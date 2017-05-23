package com.game.basicdemo.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.game.basicdemo.R;

/**
 * Created by admin on 2017/5/23.
 */

public class RadioButtonDialog extends Dialog {

    private Context context;
    private String title;

    private AdapterView.OnItemClickListener onItemClickListener;
    public RadioButtonDialog(Context context) {
        super(context);
        this.context = context;
    }

    public RadioButtonDialog(Context context,int theme){
        super(context,theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        init();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener){
        if(onItemClickListener !=null){
            this.onItemClickListener = onItemClickListener;
        }
    }

    RadioButton rbtn_BroadcastClose;
    RadioButton rbtn_b15;
    RadioButton rbtn_b30;
    RadioButton rbtn_b45;
    RadioButton rbtn_b60;

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_broadcast,null);
        setContentView(view);
        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(title);
        RadioGroup group = (RadioGroup) view.findViewById(R.id.radioGroup);
        rbtn_BroadcastClose = (RadioButton) view.findViewById(R.id.rbtn_BroadcastClose) ;

        rbtn_b15 = (RadioButton) view.findViewById(R.id.rbtn_b15);
        rbtn_b30 = (RadioButton) view.findViewById(R.id.rbtn_b30);
        rbtn_b45 = (RadioButton) view.findViewById(R.id.rbtn_b45);
        rbtn_b60 = (RadioButton) view.findViewById(R.id.rbtn_b60);
        group.setOnCheckedChangeListener(listener);

        Window dialogWindow = getWindow();
        WindowManager manager = ((Activity) context).getWindowManager();
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        Display d = manager.getDefaultDisplay();
        params.width = (int) (d.getWidth()*0.8);
        dialogWindow.setAttributes(params);
    }

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId == rbtn_BroadcastClose.getId()){
                    dismiss();
            }else if(checkedId ==rbtn_b15.getId()){

            }else if(checkedId ==rbtn_b30.getId()){

            }
            else if(checkedId ==rbtn_b45.getId()){

            }
            else if(checkedId ==rbtn_b60.getId()){

            }
        }

    };

}
