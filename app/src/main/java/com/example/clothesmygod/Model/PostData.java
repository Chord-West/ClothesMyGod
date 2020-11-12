package com.example.clothesmygod.Model;

import android.graphics.Bitmap;

public class PostData {
    private String uid;
    private String title;
    private boolean check=false;

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
