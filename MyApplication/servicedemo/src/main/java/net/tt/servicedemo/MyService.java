package net.tt.servicedemo;


import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by admin on 2017/4/26.
 */

public class MyService extends Service {

    public static final String TAG = "MyService";

    //private MyBinder myBinder = new MyBinder();
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("MyService","MyService thread id is "+Thread.currentThread().getId());

       /* Intent  intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Notification notification = new Notification.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("通知")
                .setContentText("我是谁")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .build();
        startForeground(1,notification);*/
       /* try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Log.d(TAG,"onCreate() executed");
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.d(TAG,"onStartCommand executed");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //开始执行后台任务
            }
        }).start();
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() executed");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    class MyBinder extends Binder{
        public void startDownload(){
            Log.d("HACK-TAG","startDownload()  executed");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    //执行具体的下载任务
                }
            }).start();
        }
    }
    MyAIDLService.Stub myBinder = new MyAIDLService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int plus(int a, int b) throws RemoteException {
            return a+b;
        }

        @Override
        public String toUpperCase(String str) throws RemoteException {
            if(str !=null){
                return str.toUpperCase();
            }
            return null;
        }
    };

}
