package com.game.basicdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtils {

	/**
	 * 保存数据到share preference
	 * @param fileName
	 * @param key
	 * @param value
	 */
	public static void saveDataToSharePreferences(Context context, String fileName, String key, Object value) {
		SharedPreferences share = context.getSharedPreferences(fileName, 0);
		SharedPreferences.Editor edit = share.edit(); // 编辑文件
		edit.putString(key, value.toString());
		edit.commit();
	}
	
	/**
	 * 从share preference 读取数据
	 * @param fileName 保存的文件名
	 * @param key      保存的key
	 * @return value   保存的值
	 */
	public static String getStringDataFromSharePreferences(Context context, String fileName, String key) {
		SharedPreferences share = context.getSharedPreferences(fileName, 0);
		return share.getString(key, "");
	}
	
	/**
	 * 删除share preferences文件
	 */
	public static void deleteDataFromSharePreferences(Context context, String fileName){
		SharedPreferences share = context.getSharedPreferences(fileName, 0);
		SharedPreferences.Editor edit = share.edit();
		edit.clear();
		edit.commit();
	}
	
	/**
	 * 删除指定文件对应key
	 * @param fileName
	 * @param key
	 */
	public static void deleteDataFromSharePreferences(Context context, String fileName, String key){
		SharedPreferences share = context.getSharedPreferences(fileName, 0);
		SharedPreferences.Editor edit = share.edit();
		edit.remove(key);
		edit.commit();
	}
}
	