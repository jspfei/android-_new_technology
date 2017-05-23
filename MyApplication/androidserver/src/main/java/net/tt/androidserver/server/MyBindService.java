package net.tt.androidserver.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by admin on 2017/5/15.
 */

public class MyBindService extends Service {

    private static final String TAG = "MyBindService";

    private MyBinder mBinder = new MyBinder();

    public class MyBinder extends Binder{
        public MyBindService getService(){
            return MyBindService.this;
        }
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public boolean onUnbind(Intent intent){
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return mBinder;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}
