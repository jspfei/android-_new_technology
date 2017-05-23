package net.tt.androidserver.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import net.tt.androidserver.R;
import net.tt.androidserver.server.MyMessageServer;

public class CMessengerActivity extends Activity {
    private static final String TAG = "HACK-TAG";

    private ServiceConnection sc = new MyServiceConnection();

    private boolean mBound;

    private Messenger mServerMessenger;

    private Handler mClientHandler = new MyClientHandler();

    private Messenger mClientMessenger = new Messenger(mClientHandler);

    private class MyClientHandler extends Handler{
        @Override
        public void handleMessage(Message msg){
           if (msg.what == MyMessageServer.MSG_FROM_SERVER_TO_CLIENT) {
               Log.i(TAG, "handleMessage: reveive msg from server");
            }
        }
    }


    private class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mServerMessenger = new Messenger(service);

            mBound = true;
            Log.i(TAG, "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected: ");
            mBound = false;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmessenger);

        findViewById(R.id.bindService1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CMessengerActivity.this,MyMessageServer.class);
                bindService(intent,sc, Context.BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.unBindService1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excuteUnbindService();
            }
        });

        findViewById(R.id.sendToMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayHello();
            }

        });

    }

    private void sayHello() {
        if(!mBound){
            return;
        }
        Message msg = Message.obtain(null,MyMessageServer.MSG_FROM_CLIENT_TO_SERVER,0,0);
        msg.replyTo = mClientMessenger;

        try {
            mServerMessenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void excuteUnbindService() {
        if(mBound){
            unbindService(sc);
            mBound =false;
        }
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        excuteUnbindService();
    }
}
