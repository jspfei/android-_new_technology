package net.tt.greendao;

import android.app.Application;
import android.content.Context;

import com.sky.downloader.greendao.DaoMaster;
import com.sky.downloader.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by admin on 2017/4/17.
 */

public class GreenDaoApp extends Application {
    private static DaoSession mDaoSession;
    private String DATA_BASE_NAME ="user.db";
    @Override
    public void onCreate() {
        super.onCreate();
        //配置数据库
        setupDataBase(this);
    }
    private void setupDataBase(Context context){
        //创建数据库 user.db
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context,DATA_BASE_NAME);
        //获取可写数据库
        Database db = openHelper.getWritableDb();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        mDaoSession = daoMaster.newSession();
    }
    public static DaoSession getDaoSession(){
        return mDaoSession;
    }
}
