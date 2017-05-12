package net.tt.serviceclientdemo;

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

import net.tt.servicedemo.MyAIDLService;

public class MainActivity extends AppCompatActivity {

    private MyAIDLService myAIDLService;
    private static final String TAG = "HACK-TAG";
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAIDLService = MyAIDLService.Stub.asInterface(service);
            try{
                int result = myAIDLService.plus(50,100);
                String upperStr = myAIDLService.toUpperCase("jjjsf kkScoo");
                Log.d("HACK-TAG","result is "+result);
                Log.d("HACK-TAG","upperStr is "+upperStr);
                Log.d(TAG, "onServiceConnected: ");
                Log.i(TAG, "onServiceConnected: result is "+result);
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected: "+name.toString());
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bindService = (Button) findViewById(R.id.bind_service);
        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("net.tt.servicedemo.MyAIDLService");
                bindService(intent,connection,BIND_AUTO_CREATE);
            }
        });
    }
}
