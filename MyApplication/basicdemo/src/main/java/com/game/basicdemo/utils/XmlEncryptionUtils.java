package com.game.basicdemo.utils;

/**
 * String xml  加密解密
 * Created by admin on 2016/12/19.
 */

public class XmlEncryptionUtils {
    public static String encryptAndDecryp(String str , int key){
        //str 要加密或者解密的字符串
        char[] array = str.toCharArray();//获取字符数组
        for (int i = 0; i < array.length; i++)//遍历字符数组
        {
            array[i] = (char) (array[i] ^ key);//对每个数组元素进行异或运算，异或的值可以自己选择
        }
        //System.out.println(new String(array));//输出加密或者解密结果
        return new String(array);
    }
}
