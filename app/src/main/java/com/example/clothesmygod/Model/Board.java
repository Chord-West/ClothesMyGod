package com.example.clothesmygod.Model;

import java.net.URL;

public class Board {
    private String author;
    private String title;
    private String content;

    public Board(String author, String post_name, String post_content){}

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}
