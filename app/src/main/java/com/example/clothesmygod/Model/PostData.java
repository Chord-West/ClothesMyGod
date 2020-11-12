package com.example.clothesmygod.Model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import java.net.URL;

public class PostData {
    private String uid;
    private String title;
    private boolean check=false;
    private URL url;

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public PostData(String uid, String title) {
        this.uid = uid;
        this.title = title;
    }
    public PostData(){}

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }










}
