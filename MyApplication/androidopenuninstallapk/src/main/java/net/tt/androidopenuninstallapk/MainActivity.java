package net.tt.androidopenuninstallapk;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import net.tt.androidopenuninstallapk.utils.ChangeSoImgeHandler;
import net.tt.androidopenuninstallapk.utils.RoomUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        // 获得根容器
        LinearLayout root = (LinearLayout) findViewById(R.id.root);
        String packagename = ChangeSoImgeHandler.getApkPackageName(this);
        // 分别获得SDCARD下的APK并添加相关View到根容器
        RoomUtils roomA = new RoomUtils(this, "file:///android_asset/service1.apk", "/mnt/sdcard/");
        root.addView(roomA.getView());*/


/*
        if(copyApkFromAssets(this,"service1.apk", Environment.getExternalStorageDirectory().getAbsolutePath()+"/service1.apk")){
             AlertDialog.Builder m = new AlertDialog.Builder((this))
                     .setIcon(R.mipmap.ic_launcher).setMessage("安装？")
                     .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             Intent intent = new Intent(Intent.ACTION_VIEW);
                             intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                             intent.setDataAndType(Uri.parse("file://"+Environment.getExternalStorageDirectory().getAbsolutePath()+"/service1.apk")
                             ,"application/vnd.android.package-archive");

                             startActivity(intent);
                         }
                     });
            m.show();
        }*/
        useDexClassLoader();
    }

    public void useDexClassLoader(){
        Intent intent = new Intent("com.cn.plugin.client",null);
        PackageManager pm = getPackageManager();

        final List<ResolveInfo> plugins = pm.queryIntentActivities(intent,0);
        ResolveInfo rInfo = plugins.get(0);
        ActivityInfo aInfo= rInfo.activityInfo;
        String packageName = aInfo.packageName;
        String dexPath = aInfo.applicationInfo.sourceDir;
        String dexOutputDir = getApplicationInfo().dataDir;
        String libPath = aInfo.applicationInfo.nativeLibraryDir;

        Log.d("HACK-TAG", "packageName is : " + packageName + "\n" + "dexPath is : "
                + dexPath + "\ndexOutputDir is : " + dexOutputDir
                + "\nlibPath is : " + libPath);

        DexClassLoader classLoader = new DexClassLoader(dexPath,dexOutputDir,
                libPath,getClass().getClassLoader());

        try {
            Class<?> clazz = classLoader.loadClass(packageName+".PluginClass");

            Object obj = clazz.newInstance();
            Class<?> [] params = new Class[2];

            params[0] = Integer.TYPE;
            params[1] = Integer.TYPE;
            //反射调用function1 方法

            Method aciton = clazz.getMethod("function1",params);
            Integer ret = (Integer) aciton.invoke(obj,12,34);

            Log.i("HACK-TAG", "return value is  "+ret);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    public boolean copyApkFromAssets(Context context,String fileName,String path){
        boolean copyIsFinish =false;

        try {
            InputStream is = context.getAssets().open(fileName);
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp= new byte[1024];
            int i = 0;
            while ((i = is.read(temp))>0){
                fos.write(temp,0,i);
            }
            fos.close();
            is.close();
            copyIsFinish = true;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return copyIsFinish;
    }
}
