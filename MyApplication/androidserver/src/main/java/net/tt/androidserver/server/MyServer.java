package net.tt.androidserver.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by admin on 2017/5/15.
 */

public class MyServer extends Service {
    private static final String TAG = "MyServer";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    @Override
    public void onCreate(){
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }
    
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.i(TAG, "onStartCommand: ");
        
        String name = intent.getStringExtra("name");

        return START_STICKY;
    }


    
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}
