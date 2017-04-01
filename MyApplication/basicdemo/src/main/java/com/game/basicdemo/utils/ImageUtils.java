package com.game.basicdemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wyj on 16/5/17.
 */
public class ImageUtils {

    private static final String PATH = "/sdcard/temp/";
    private static final String LAST_PNG = "splash.png";
    public static Bitmap getLoacalBitmap() {
        try {
            FileInputStream fis = new FileInputStream(PATH + LAST_PNG);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static File saveBitmapToFile(Bitmap bitmap) {
        File file = new File(PATH, LAST_PNG);
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
        } catch (FileNotFoundException var12) {
            var12.printStackTrace();
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException var11) {
                    var11.printStackTrace();
                }
            }

        }

        return file;
    }
}
