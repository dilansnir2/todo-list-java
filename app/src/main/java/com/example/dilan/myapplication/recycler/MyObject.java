package com.example.dilan.myapplication.recycler;

public class MyObject {
    private String text;
    private String imageUrl;

    public MyObject(String text, String imageUrl){
        this.text = text;
        this.imageUrl = imageUrl;
    }

    public String getText(){
        return this.text;
    }

    public String getImageUrl(){
        return this.imageUrl;
    }
}
