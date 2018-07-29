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
    private String title;
    private Boolean status;

    public Task(){

    }

    public Task(int id , String content, String date, Boolean status, String title){
        this.ID = id;
        this.content = content;
        this.date = date;
        this.status = status;
        this.title = title;
    }

    public String getContent(){
        return this.content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getTitle(){
        return this.title;
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

    public static List showTasks(Context context, String filter){

        List<MyObject> tasks = new ArrayList<>();
        TasksBDD tasksBdd = new TasksBDD(context);
        tasksBdd.open();
        ArrayList<Map<String, String>> tasksFromBdd;
        System.out.println(filter);
        if(filter == "TODO"){
            tasksFromBdd = tasksBdd.getTodoTasks();
        }else if(filter == "DONE") {
            tasksFromBdd = tasksBdd.getDoneTasks();
        }else{
            tasksFromBdd = tasksBdd.getTasks();
        }

        for (int i = 0; i < tasksFromBdd.size(); i++) {
            Boolean status = false;
            if(Integer.parseInt(tasksFromBdd.get(i).get("status")) == 1){
                status = true;
            }
            System.out.println(status);
            tasks.add(new MyObject(tasksFromBdd.get(i).get("content"), tasksFromBdd.get(i).get("id"), tasksFromBdd.get(i).get("date"), status, tasksFromBdd.get(i).get("title")));
        }
        return tasks;
    }

}
