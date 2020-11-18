package com.example.clothesmygod.Model;

import com.google.firebase.database.Exclude;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private String author;
    private String title;
    private String content;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Board(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }
    public Board(){}

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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("author", author);
        result.put("title", title);
        result.put("content", content);
        return result;
    }
}
