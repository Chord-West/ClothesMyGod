package com.example.clothesmygod.Model;

import com.google.firebase.database.Exclude;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Board {
    public String author;
    public String title;
    public String content;

    public Board(){}

    public Board(String author, String title, String content){
        this.author=author;
        this.title=title;
        this.content=content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
