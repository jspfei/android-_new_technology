package com.jf.technology;

import java.io.RandomAccessFile;

/**
 * Created by admin on 2017/3/24.
 */

public class ReaFile {
    public static void main(String[] args) {
        try {
            RandomAccessFile raFile = new RandomAccessFile("C:\\Users\\admin\\Desktop\\捕鱼破解密码\\so\\libcocos2dcpp.so", "rw");
            raFile.seek(0x1FBA20); // 利用RandomAccessFile定位到第101个字节，之后再读文件
            raFile.write('1');
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
