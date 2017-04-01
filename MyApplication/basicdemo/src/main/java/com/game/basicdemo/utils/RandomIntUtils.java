package com.game.basicdemo.utils;

/**
 * 随机数生成工具
 * Created by admin on 2016/12/22.
 */

public class RandomIntUtils {

    //随机返回 max  和 min 中间一个数
    public static int getRandomInt(int max,int min){
        int Min = min;
        int Max = max;
        int result = Min + (int)(Math.random() * ((Max - Min)));
        return result;
    }
}
