package net.tt.androidserver.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import net.tt.androidserver.R;
import net.tt.androidserver.server.MyBindService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BoundActivity extends Activity {

    private static final String TAG = "BoundActivity";

    private ServiceConnection sc = new MyServiceConnection();
    private MyBindService.MyBinder myBinder;
    private MyBindService myBindService;
    private boolean mBound;

    private class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Log.i(TAG, "onServiceConnected: ");
            myBinder = (MyBindService.MyBinder) service;
            myBindService = myBinder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
            Log.i(TAG, "onServiceDisconnected: ");

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound);


         findViewById(R.id.bindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BoundActivity.this,MyBindService.class);
                bindService(intent,sc, Context.BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.unBindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excuteUnbindService();
            }
        });


        String cmd = "adb shell";
        String cmd1 =  "am start -n com.android.browser/com.android.browser.BrowserActivity";

        try {
            execCommand(cmd);
            execCommand(cmd1);
        } catch (IOException e) {

        }

    }
    public void execCommand(String command) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec(command);
        try {
            if (proc.waitFor() != 0) {
                System.err.println("exit value = " + proc.exitValue());
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line+"-");
            }
            System.out.println(stringBuffer.toString());

        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }
    private void excuteUnbindService() {
        if (mBound) {
            unbindService(sc);
            mBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG, "in onDestroy");
        excuteUnbindService();
    }
}
