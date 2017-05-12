package com.jf.uidemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jf.uidemo.activity.DialogManagerActivity;
import com.jf.uidemo.view.CustomDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_dialog;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("HACK-TAG","onCreate  ");
        Log.d("HACK-TAG","onCreate  ");
        setContentView(R.layout.activity_main);

        mContext = this;

        button_dialog = (Button) findViewById(R.id.button_dialog);
        button_dialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_dialog){
            Log.i("HACK-TAG","onClick  ");
            Log.d("HACK-TAG","onClick  ");
            Intent intent = new Intent();
            intent.setClass(mContext, DialogManagerActivity.class);
            startActivity(intent);
        }
    }
}
