package com.game.basicdemo.utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.ViewGroup.LayoutParams;

public class SystemUtils {

	private static void scaleHeight(LayoutParams params, int width){
		
		int oHeight = params.height;
		int oWidth = params.width;
		
		params.height = oHeight * (width/oWidth);
		params.width = width;
		
	}
	
	/**
	 * 获取屏幕宽度
	 */
	public static int getScreenWidth(Activity activity){
		
		DisplayMetrics dm = new DisplayMetrics();

		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

		return dm.widthPixels;
	}

}
