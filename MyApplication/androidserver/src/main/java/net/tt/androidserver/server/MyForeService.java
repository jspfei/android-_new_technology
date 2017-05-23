package net.tt.androidserver.server;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import net.tt.androidserver.R;
import net.tt.androidserver.activity.BoundActivity;

public class MyForeService extends Service {
    private static final String TAG = "MyForeService";
    public MyForeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent,int flag, int startId){
        Log.i(TAG, "onStartCommand: ");
        String name = intent.getStringExtra("name");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentTitle("title")
                .setContentText("content")
                .setSmallIcon(R.mipmap.ic_launcher);

        Intent intent1 = new Intent(this,BoundActivity.class);

        PendingIntent noPendingIntent = PendingIntent.getActivity(this,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(noPendingIntent);

        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //manager.notify(1,notification);
        startForeground(1,notification);

        return START_REDELIVER_INTENT;
    }
    @Override
    public void onDestroy(){

    }
}
