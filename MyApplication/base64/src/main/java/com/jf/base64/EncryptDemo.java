package com.jf.base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 右键执行Java ，将目录下的文件 xx.jpg  加密 生成 xx.dat
 * 将xx.dat 放入 assets/picture下
 * 通过 AssetsImageUtils.readBitmap 方法解密文件
 * Created by admin on 2017/2/16.
 */

public class EncryptDemo {
    /**
     * @param args
     */
    public static void main(String[] args) {
        String str = "Hello World";
        try{

            File load = new File("E:\\file\\gameLogo.spr");      //原图
            File result = new File("E:\\file\\ss.spr");    //加密后得到的文件
            wrapping(load,result);
        } catch(Exception e){
            e.printStackTrace();
        }
     /*   try{
            byte[] encodeBase64 =  Base64.encode(str.getBytes("UTF-8"),1);
            System.out.println("RESULT: " + new String(encodeBase64));
            byte[] sd = Base64.decode(encodeBase64,8);
            System.out.println("RESULT: " + new String(sd));
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }*/
    }
    public static void wrapping(File load, File result)  throws Exception {
        InputStream fis = new FileInputStream(load);
        byte[] bytes= null;
        bytes= new byte[fis.available()];    //in.available()是得到文件的字节数
        fis.read(bytes);
        fis.close();
        outFile(result,bytes);
    }
    public static void outFile(File file , byte[] bytes) throws Exception{
        OutputStream fos = new FileOutputStream(file);

        fos.write(bytes);
        fos.flush();
        fos.close();
    }

    public static byte[] addBytes(byte[] data1, byte[] data2) {
        byte[] data3 = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;

    }
}
