package net.tt.androidserver.server;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by admin on 2017/5/16.
 */

public class MyMessageServer extends Service {

    private static final String TAG = "HACK-TAG";

    public static final int MSG_FROM_CLIENT_TO_SERVER = 1;
    //<editor-fold desc="Description">
    public static final int MSG_FROM_SERVER_TO_CLIENT = 2;
    //</editor-fold>

    private Messenger mClientMessager;
    private Messenger mServerMessager = new Messenger(new ServerHandler());

    class ServerHandler extends Handler {
        @Override
        public void handleMessage(Message msg){
            Log.i(TAG, "handleMessage: thead name :"+Thread.currentThread().getName());

            switch (msg.what){
                case MSG_FROM_CLIENT_TO_SERVER:
                    Log.i(TAG, "handleMessage: receive msg from server");
                    mClientMessager = msg.replyTo;

                    //service 发送消息给client

                    Message toClientMsg = Message.obtain(null,MSG_FROM_SERVER_TO_CLIENT);

                    try {
                        Log.i(TAG, "handleMessage: server begin send msg to client");
                        mClientMessager.send(toClientMsg);
                    } catch (RemoteException e) {

                        e.printStackTrace();
                    }
            }
        }

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mServerMessager.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent){
        Log.i(TAG, "onUnbind: ");

        return  super.onUnbind(intent);
    }

    @Override
    public void onDestroy(){
        Log.i(TAG, "onDestroy: ");

        super.onDestroy();
    }
}
