package com.game.basicdemo;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.game.basicdemo.hanlder.ScreenManager;


public class ExitActivity extends AppCompatActivity {
    private int close_flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        ScreenManager.getScreenManager().pushActivity(this);

        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb1){
                    close_flag = 0;
                }else if(checkedId == R.id.rb2){
                    close_flag = 1;
                }else if(checkedId == R.id.rb3){
                    close_flag = 2;
                }else{
                    close_flag = 3;
                }
            }
        });

    }
    public void onExit(View view){
        Toast.makeText(ExitActivity.this,"  "+close_flag,Toast.LENGTH_SHORT).show();
        switch (close_flag){
            case 0:
                closeAll_3();
                break;
            case 1:
                finish();
                break;
            case 2:
                closeAll_2();
                break;
            case 3:
                closeAll_4();
                break;

        }
    }


    private void closeAll_2(){
        ActivityManager am = (ActivityManager)getSystemService (Context.ACTIVITY_SERVICE);
        am.restartPackage(getPackageName());
    }
    private void closeAll_3(){
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
    private void closeAll_4(){
        ScreenManager.getScreenManager().popAllActivityExceptOne(ExitActivity.class);
    }
}
