package net.tt.twoactivitytest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
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
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.sub);


       // finishButton = (Button)findViewById(R.id.button);
        //finishButton.setOnClickListener(this);

        Log.i("HACK-TAG", "Sub Activity Created.");
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
                Intent intent = new Intent("shy.luo.task.mainactivity");
                startActivity(intent); 
                onResume();

            default:
                break;
        }
        Log.i("HACK-TAG","subactivity  onkeydown");
        return super.onKeyDown(keyCode, event);
    }


}
