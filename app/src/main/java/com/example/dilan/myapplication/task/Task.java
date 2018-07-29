package com.example.dilan.myapplication.task;

import android.content.Context;

import com.example.dilan.myapplication.recycler.MyObject;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private int ID;
    private String content;

    public Task(){

    }

    public Task(int id , String content){
        this.ID = id;
        this.content = content;
    }

    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
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
        ArrayList<String> tasksFromBdd = tasksBdd.getTasks();
        for (int i = 0; i < tasksFromBdd.size(); i++) {
            tasks.add(new MyObject(tasksFromBdd.get(i), ""));
        }
        return tasks;
    }

}
