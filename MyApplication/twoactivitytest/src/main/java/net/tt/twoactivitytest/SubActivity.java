package net.tt.twoactivitytest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import ss.jj.R;

/**
 * Created by admin on 2017/5/11.
 */

public class SubActivity extends Activity implements View.OnClickListener {
    private final static String LOG_TAG = "net.tt.twoactivitytest.SubActivity";

    private Button finishButton = null;
    private static MainActivity mact;

    @Override
    public void onCreate(Bundle savedInstanceState) {

         sendBoar("create");
        super.onCreate(savedInstanceState);
         requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setVisible(false);
        // setContentView(R.layout.sub);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);

       // finishButton = (Button)findViewById(R.id.button);
        //finishButton.setOnClickListener(this);
       // longTimeAction();
        Intent intent = new Intent(SubActivity.this,
                ExampleService.class);
        startService(intent);
        longTimeStart();
        Log.i("HACK-TAG", "Sub Activity Created.");
    }

    private void longTimeStart(){
      /*  for(int i=0;i<55555;i++){
            Log.i("HACK-TAG", "Sub Activity longTimeAction.");
        }*/
    }

    public void sendBoar(String key){
        onWindowFocusChanged(false);
        Log.i("HACK-TAG", "SubActivity cmd = " + key);
        Intent intent = new Intent();
        intent.setAction("net.tt.jf.broadcast");
        Log.i("HACK-TAG", "SubActivity cmd 1 = " + key);
        intent.putExtra("cmd", key);
        SubActivity.this.sendBroadcast(intent);

    }

    @Override
    public void onClick(View v) {
        if(v.equals(finishButton)) {

        }
    }
    public static   void setMainActivity(MainActivity act){
         mact = act;
        Log.i("HACK-TAG","mact   "+mact);
    }
    @Override
    public boolean onKeyDown (int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                /*
                * 回调 MainActivity 会导致当前Activity finish();
                * */
              /*  Intent intent = new Intent("shy.luo.task.mainactivity");
                startActivity(intent);*/

                /*
                * 调用 onPause().音量键可以监听
                * */
              //  onPause();

            default:
                break;
        }
        Log.i("HACK-TAG","subactivity  onkeydown");
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onResume(){
        super.onResume();
        sendBoar("resume");
    }

    @Override
    public void onPause(){
        super.onPause();
       sendBoar("pause");
    }

    @Override
    public void onDestroy(){

        super.onDestroy();
    }
}
