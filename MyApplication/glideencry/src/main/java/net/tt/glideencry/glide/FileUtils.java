package net.tt.glideencry.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.bumptech.glide.util.LruCache;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2017/4/17.
 */

public class FileUtils {
    static int MAXMEMONRY = (int) (Runtime.getRuntime() .maxMemory() / 1024);
    private static LruCache<String, byte[]> mMemoryCache = new LruCache<String,byte[]>(MAXMEMONRY/1024);;
    /**
     * 启动图片下载线程
     */
    public static byte[] Bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
        //  读取缓存图片
    public static byte[] readFileData(Context context, String fileName){

        byte[] buffer= null;
        buffer= mMemoryCache.get(fileName);
        if(buffer == null){
            byte[] eencyByte= null;
            try{
                String path = context.getFilesDir().getAbsolutePath() ;
                File file = new File(path +"/"+fileName) ;
                Log.i("HACK-TAG","readFileData " +file);
                Log.i("HACK-TAG","readFileData   exists() " +file.exists());
                FileInputStream fin = context.openFileInput(fileName);
                int length = fin.available();
                eencyByte = new byte[length];
                fin.read(eencyByte);
                fin.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            //界面 图片放人缓存中
            byte[] dencyByte= encyData(eencyByte);
            mMemoryCache.put(fileName,dencyByte);
            buffer =  mMemoryCache.get(fileName);
        }

        Log.i("HACK-TAG","---------------dencyByte");

        return buffer ;

    }
    //图片加密解密算法
    private static byte[] encyData(byte[] datas){
        byte[] bytes =new byte[datas.length];
        int read = 0;
        while (read<datas.length){
            byte s = datas[read];
            bytes[read] = (byte) (s^0X99);
            read = read+1;
        }
        return bytes;
    }
    //  网络下载图片byte[] 格式 加密存入 data/data/包名/files/  中
    public static void writeFileData(Context context, String fileName, byte[] bytes){
        try{
            Log.i("HACK-TAG","---------------encyByte");
            //界面 直接放入缓存中。减少加载图片过程
            mMemoryCache.put(fileName,bytes);
            //界面 加密保存到文件中
            byte[] encyByte= encyData(bytes);

            String path = context.getFilesDir().getAbsolutePath() ;
            File file = new File(path +"/"+fileName) ;
        /*    Log.i("HACK-TAG","file " +file);
            Log.i("HACK-TAG","file.exists() " +file.exists());*/
            if(!file.exists()){
                FileOutputStream fout =context.openFileOutput(fileName, MODE_PRIVATE);
                fout.write(encyByte);
                fout.close();
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static Boolean judeFileExists(Context context, String filenamemd5){
        String path = context.getFilesDir().getAbsolutePath() ;
        File file = new File(path +"/"+filenamemd5) ;

        if(file.exists()){
            return true;
        }else {
            return false;
        }
    }
    public static Bitmap getPicFromBytes(byte[] bytes,
                                         BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
                        opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }
}
