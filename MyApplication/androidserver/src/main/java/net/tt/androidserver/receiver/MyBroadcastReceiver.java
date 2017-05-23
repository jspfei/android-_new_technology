package net.tt.androidserver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by admin on 2017/5/16.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "MyBroadcastReceiver";
    private static int m  =1 ;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, " intent  "+intent);
        String name = intent.getStringExtra("name");
        Log.i(TAG, "name  "+name);
        m++;

        Bundle bundle= intent.getExtras();
    }
}
