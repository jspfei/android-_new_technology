package net.tt.glideencry.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by admin on 2017/4/17.
 */

public class GlideSaveImage {

    private ImageView imageView;
    private Bitmap bitmaps;
    private int mResourcId;
    public static GlideSaveImage with(){
        return new GlideSaveImage();
    }

    //根据Message 加载显示图片
    private Handler handler = new Handler(){
        public void handleMessage(Message msg)
        {
            switch(msg.what)
            {
                case 1:
                    imageView.setImageBitmap(bitmaps);
                    break;
                case 2:
                    imageView.setImageResource(mResourcId);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
    public  void onDownLoad1(String url , final Context context , ImageView iv , int resourcId) {
        imageView = iv;
        //设置 图片默认显示图片
        mResourcId = resourcId;
        Message message1=new Message();
        message1.what=2;
        handler.sendMessage(message1);


        //更加URL 生成 对应的md5 值 ，将其作为图片名称来  保存图片
        final String fileMd5 = Md5Utils.getMD5(url);
        //判断是否缓存了图片
        if( FileUtils.judeFileExists(context,fileMd5)){
            //缓存  就直接调用
            bitmaps = FileUtils.getPicFromBytes(FileUtils.readFileData(context,fileMd5),null);
            Message message=new Message();
            message.what=1;
            handler.sendMessage(message);

        }else{
            //未缓存  网络加载 .保存文件
            DownLoadImageService service = new DownLoadImageService(context,
                    url,
                    new ImageDownLoadCallBack() {

                        @Override
                        public void onDownLoadSuccess(File file) {
                        }
                        @Override
                        public void onDownLoadSuccess(Bitmap bitmap) {
                            //网络下载成功显示图片
                          //  bitmaps = FileUtils.getPicFromBytes(FileUtils.readFileData(context,fileMd5),null);
                            bitmaps = bitmap;
                            Message message=new Message();
                            message.what=1;
                            handler.sendMessage(message);
                        }

                        @Override
                        public void onDownLoadFailed() {
                            //网络下载图片失败 。显示默认图片
                            Message message=new Message();
                            message.what=2;
                            handler.sendMessage(message);
                        }
                    });
            //启动图片下载线程
            new Thread(service).start();
        }
    }
}
