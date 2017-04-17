package net.tt.glideencry.glide;

import android.graphics.Bitmap;

import java.io.File;

/**
 * Created by admin on 2017/4/17.
 */

public interface ImageDownLoadCallBack {
    void onDownLoadSuccess(File file);
    void onDownLoadSuccess(Bitmap bitmap);

    void onDownLoadFailed();
}
