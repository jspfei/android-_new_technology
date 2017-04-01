package com.game.androidbasicskill;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * android 广播的使用
 * */
public class AndroidBroadcastReceiverActivity extends Activity {
    private final String ACTION_NAME = "发送广播";
    private Button mBtnMessageEvent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_broadcast_receiver);
        //注册广播
        registerBroadcastReceiver();
        //第三步：触发响应
        mBtnMessageEvent =(Button) findViewById(R.id.btn_message_event);
        mBtnMessageEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACTION_NAME);
                intent.putExtra("jf","发送广播，相当于在这里传送数据");
                sendBroadcast(intent);
            }
        });


    }

    //第一步：定义一个BroadcastReceiver广播接收类
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(ACTION_NAME)){
                Toast.makeText(AndroidBroadcastReceiverActivity.this,
                        "处理action名字相对应的广播", Toast.LENGTH_SHORT).show();
            }
        }
    };

    //第二步：注册该广播：
    public void registerBroadcastReceiver(){
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(ACTION_NAME);
        //注册广播
        registerReceiver(mBroadcastReceiver,myIntentFilter);
    }
}
