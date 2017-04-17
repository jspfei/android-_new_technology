package net.tt.glideencry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 右键执行Java ，将目录下的文件 xx.jpg  加密 生成 xx.dat
 * 将xx.dat 放入 assets/picture下
 * 通过 AssetsImageUtils.readBitmap 方法解密文件
 * Created by admin on 2017/2/16.
 */

public class EncryptDemo {

    public static final int XOR_CONST = 0X99; // 异或密钥

    /**
     * @param args
     */
    public static void main(String[] args) {

        String[] filename = new String[]{"3BBCE7F280DF2E866AF8AB61D2597508"};
        //,

        for(int i = 0;i<filename.length;i++){
            File load = new File("E:\\file\\"+filename[i]+".jpg");      //原图
            File reload = new File("E:\\file\\"+filename[i]+"2.jpg");   //解密后得到的原图

            try {
                encryptImg(load, reload);   // 加密原图
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
      /*  File load = new File("E:\\file\\ad_head.jpg");      //原图
        File result = new File("E:\\file\\ad_head.dat");    //加密后得到的文件
        File reload = new File("E:\\file\\ad_head2.jpg");   //解密后得到的原图

        try {
            encryptImg(load, result);   // 加密原图
            encryptImg(result, reload); // 对加密后的文件再加密得到原图
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


    /**
     * 文件流异或加密
     * @param load
     * @param result
     * @throws Exception
     */
    public static void encryptImg(File load, File result) throws Exception {
        FileInputStream fis = new FileInputStream(load);
        FileOutputStream fos = new FileOutputStream(result);

        int read;
        while ((read = fis.read()) > -1) {
            fos.write(read ^ XOR_CONST);    //进行异或运算并写入结果
        }
        fos.flush();
        fos.close();
        fis.close();
    }
}
