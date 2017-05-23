package net.tt.androidopenuninstallapk.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by admin on 2017/5/17.
 */

public class ChangeSoImgeHandler {
    private static PackageInfo info;
    public static String getApkPackageName(Context context){
        String packageNames="";
        try {
            info = context.getPackageManager().getPackageInfo( context.getPackageName(), 0);
            // 当前版本的包名
            packageNames = info.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageNames;
    }

}
