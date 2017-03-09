package com.jf.uidemo.activity;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jf.uidemo.R;
import com.jf.uidemo.view.CustomDialog;

public class DialogManagerActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button_custom_dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_manager);

        button_custom_dialog = (Button) findViewById(R.id.button_custom_dialog);
        button_custom_dialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_custom_dialog:
                openCustomDialog();
                break;
            default:
                break;
        }
    }
    private void openCustomDialog(){
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.setMessage("自定义文本");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //设置你的操作
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
