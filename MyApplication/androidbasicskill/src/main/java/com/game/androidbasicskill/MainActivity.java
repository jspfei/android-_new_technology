package com.game.androidbasicskill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView broadcastReceiver = (TextView) findViewById(R.id.text_android_broadcast_receiver);
        broadcastReceiver.setOnClickListener(this);
        TextView broadcastReflection = (TextView) findViewById(R.id.text_android_reflection);
        broadcastReflection.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.text_android_broadcast_receiver){
            Intent intent = new Intent(MainActivity.this,AndroidBroadcastReceiverActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.text_android_reflection){
            Toast.makeText(this,"测试Reflection 反射类",Toast.LENGTH_SHORT).show();
        }
    }
}
