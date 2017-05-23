package net.tt.androidserver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import net.tt.androidserver.activity.BoundActivity;
import net.tt.androidserver.activity.CMessengerActivity;
import net.tt.androidserver.activity.JavaCmdShellActivity;
import net.tt.androidserver.server.MyForeService;
import net.tt.androidserver.server.MyIntentService;
import net.tt.androidserver.server.MyServer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button startBtn;
    private Button stopBtn;

    private Intent serviceIntent;
    private Activity otherActivity;
    public MainActivity(){
        super();
    }
    public MainActivity(Context context){
        super();
        this.otherActivity =(Activity) context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = (Button) findViewById(R.id.startServer);
        stopBtn = (Button) findViewById(R.id.stopServer);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceIntent = new Intent(MainActivity.this, MyServer.class);

                startService(serviceIntent);
            }
        });

        stopBtn .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(serviceIntent);
            }
        });

        findViewById(R.id.bindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BoundActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.messageService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   Intent  intent = new Intent(MainActivity.this, CMessengerActivity.class);
                startActivity(intent);*/

             /*   Intent  intent = new Intent(MainActivity.this, MyIntentService.class);
                startService(intent);   */

              /*  Intent  intent = new Intent(MainActivity.this, MyForeService.class);
                startService(intent);*/

             //   IsSoftLink.main(null);

                Intent intent = new Intent(MainActivity.this,JavaCmdShellActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG, "in onDestroy");
    }

}
