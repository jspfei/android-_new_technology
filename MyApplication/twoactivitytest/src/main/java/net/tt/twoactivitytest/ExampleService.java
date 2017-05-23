package net.tt.twoactivitytest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by admin on 2017/5/12.
 */

public class ExampleService extends Service {
    private static final String TAG = "ExampleService";

    @Override
    public void onCreate(){
        Log.i("HACK-TAG",TAG+" onCreate");
        super.onCreate();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<400;i++){
                    Log.i("HACK-TAG", "Sub Activity longTimeAction.");
                }
            }
        }).start();
    }

    @Override
    public void onStart(Intent intent,int startId){
        super.onStart(intent,startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onDestroy(){
        super.onDestroy();
    }
    class ServiceBinder extends Binder {
        public ExampleService getService(){
            return ExampleService.this;
        }
    }
}
