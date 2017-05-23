package net.tt.androidserver.server;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by admin on 2017/5/16.
 */

public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }
    public MyIntentService(){
        super(TAG);
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        for(int i= 0;i<500;i++)
        {
            Log.i(TAG, "onHandleIntent: i   "+i);
        }
        Log.i(TAG, "onHandleIntent: ");
        Log.i(TAG, "onHandleIntent: thread name"+Thread.currentThread().getName());
    }
}
