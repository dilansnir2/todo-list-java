package com.example.dilan.myapplication.recycler;

public class MyObject {
    private String text;
    private String id;
    private String date;
    private String title;
    private Boolean status;

    public MyObject(String text, String id, String date, Boolean status, String title){
        this.text = text;
        this.id = id;
        this.date = date;
        this.status = status;
        this.title = title;
    }

    public String getText(){
        return this.text;
    }

    public String getId(){
        return this.id;
    }

    public String getDate(){return this.date;}

    public String getTitle(){return this.title;}

    public Boolean getStatus(){ return this.status;}
}
