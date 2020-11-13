package com.example.clothesmygod.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class CodyItem {
    private String top;
    private String bottom;
    private String shoes;
    private String title;
    public CodyItem() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CodyItem(String top, String bottom, String shoes) {
        this.top = top;
        this.bottom = bottom;
        this.shoes = shoes;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public String getShoes() {
        return shoes;
    }

    public void setShoes(String shoes) {
        this.shoes = shoes;
    }

    @Override
    public String toString() {
        return "CodyItem{" +
                "top='" + top + '\'' +
                ", bottom='" + bottom + '\'' +
                ", shoes='" + shoes + '\'' +
                '}';
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("top", top);
        result.put("bottom", bottom);
        result.put("shoes", shoes);

        return result;
    }
}
