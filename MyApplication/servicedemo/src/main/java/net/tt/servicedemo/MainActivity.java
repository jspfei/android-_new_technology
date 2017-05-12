package net.tt.servicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button startService;

    private Button stopService;

    private Button bindService;

    private Button unbindService;

    private MyService.MyBinder myBinder;

    private MyAIDLService myAIDLService;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
        /*    myBinder = (MyService.MyBinder) service;
            myBinder.startDownload();*/

            myAIDLService = MyAIDLService.Stub.asInterface(service);
            try{
                int result = myAIDLService.plus(3,5);
                String upperStr = myAIDLService.toUpperCase("jjjjf  sws");
                Log.d("HACK-TAG","result is "+result);
                Log.d("HACK-TAG","upperStr is "+upperStr);
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MyService","MainActivity thread id is "+Thread.currentThread().getId());
        startService = (Button) findViewById(R.id.start_service);
        stopService = (Button) findViewById(R.id.stop_service);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent startIntent = new Intent(this,MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Log.d("MyService","click stop Service button");
                Intent stopIntent = new Intent(this,MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                Log.d("MyService","click Unbind Service button");
                unbindService(connection);
                break;
            default:
                break;
        }
    }
}
