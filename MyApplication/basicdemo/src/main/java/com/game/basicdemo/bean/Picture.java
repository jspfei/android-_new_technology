package com.game.basicdemo.bean;

/**
 * Created by admin on 2017/2/10.
 */

public class Picture {
    private String title;
    private int imageId;
    public Picture(){
    }
    public Picture(String title,int imageId){
        super();
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
