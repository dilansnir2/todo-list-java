package com.example.dilan.myapplication.task;

import android.content.Context;

import com.example.dilan.myapplication.recycler.MyObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Task {
    private int ID;
    private String content;
    private String date;

    public Task(){

    }

    public Task(int id , String content, String date){
        this.ID = id;
        this.content = content;
        this.date = date;
    }

    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public int getID(){
        return this.ID;
    }

    public void setID(int id){
        this.ID = id;
    }

    public static List showTasks(Context context){
        List<MyObject> tasks = new ArrayList<>();
        TasksBDD tasksBdd = new TasksBDD(context);
        tasksBdd.open();
        ArrayList<Map<String, String>> tasksFromBdd = tasksBdd.getTasks();
        for (int i = 0; i < tasksFromBdd.size(); i++) {
            tasks.add(new MyObject(tasksFromBdd.get(i).get("content"), tasksFromBdd.get(i).get("id"), tasksFromBdd.get(i).get("date")));
        }
        return tasks;
    }

}
