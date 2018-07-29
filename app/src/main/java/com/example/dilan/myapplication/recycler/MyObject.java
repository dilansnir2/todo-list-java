package com.example.dilan.myapplication.recycler;

public class MyObject {
    private String text;
    private String id;

    public MyObject(String text, String id){
        this.text = text;
        this.id = id;
    }

    public String getText(){
        return this.text;
    }

    public String getId(){
        return this.id;
    }
}
