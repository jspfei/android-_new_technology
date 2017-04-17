package net.tt.greendao.bean;

import com.sky.downloader.greendao.UserDao;

import net.tt.greendao.GreenDaoApp;

import java.util.List;

/**
 * Created by admin on 2017/4/17.
 */

public class UserDataOp {
    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param user
     */
    public static void insertUser(User user){
        GreenDaoApp.getDaoSession().getUserDao().insertOrReplace(user);
    }
    /**
     *删除数据
     *
     * @param id
     */
    public static void deleteUser(Long id){
        GreenDaoApp.getDaoSession().getUserDao().deleteByKey(id);;
    }
    /**
     * 更新数据
     *
     * @param user
     */
    public static void updateUser(User user){
        GreenDaoApp.getDaoSession().getUserDao().update(user);
    }

    /**
     * 查询条件为 name = str
     *
     * @param name
     * */
    public static List<User> queryUserByName(String name){
        return GreenDaoApp.getDaoSession().getUserDao().queryBuilder().where(UserDao.Properties.Name.eq(name)).list();
    }

    /**
     * 查询全部
     * */
    public static List<User> queryAll(){
        return GreenDaoApp.getDaoSession().getUserDao().loadAll();
    }

}
