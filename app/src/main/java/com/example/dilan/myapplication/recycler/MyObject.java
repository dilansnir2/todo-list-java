package com.example.dilan.myapplication.recycler;

public class MyObject {
    private String text;
    private String id;
    private String date;

    public MyObject(String text, String id, String date){
        this.text = text;
        this.id = id;
        this.date = date;
    }

    public String getText(){
        return this.text;
    }

    public String getId(){
        return this.id;
    }

    public String getDate(){return this.date;}
}
