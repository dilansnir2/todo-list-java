package com.example.dilan.myapplication.task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dilan.myapplication.MyDBHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TasksBDD {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todolists.db";

    private static final String TABLE = "task";
    private static final String COL_ID = "ID";
    private static final String COL_CONTENT = "content";
    private static final int NUM_COL_ID = 0;
    private static final int NUM_COL_CONTENT = 1;

    private SQLiteDatabase bdd;

    private MyDBHandler maBaseSQLite;

    public TasksBDD(Context context){
        maBaseSQLite = new MyDBHandler(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertTask(Task task){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        values.put(COL_CONTENT, task.getContent());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE, null, values);
    }

    public int updateTask(int id, String content){
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel livre on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_CONTENT, content);
        return bdd.update(TABLE, values, COL_ID + " = " +id, null);
    }

    public int removeTaskWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE, COL_ID + " = " +id, null);
    }

    public ArrayList<Map<String, String>> getTasks(){
        Cursor c = bdd.rawQuery("SELECT * FROM task", null);
        return cursorToTasks(c);
    }

    public Map<String, String>  getTask(int ID){
        Cursor c = bdd.rawQuery("SELECT * FROM task WHERE ID = "+ID, null);
        return cursorToTask(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private ArrayList<Map<String, String>> cursorToTasks(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0) {
            return new ArrayList<Map<String, String>>();
        }
        ArrayList<Map<String, String>> array = new ArrayList<Map<String, String>>();
        //Sinon on se place sur le premier élément
        if(c.moveToFirst()){
            while( !c.isAfterLast()){
                Map<String, String> task = new HashMap<String, String>();
                task.put("id", c.getString( c.getColumnIndex("ID")));
                task.put("content", c.getString( c.getColumnIndex("content")));
                array.add(task);
                c.moveToNext();
            }
        }
        return array;
    }

    private Map<String, String> cursorToTask(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0) {
            return new HashMap<>();
        }
        Map<String, String> map = new HashMap<String, String>();
        //Sinon on se place sur le premier élément
        if(c.moveToFirst()){
            map.put("id", c.getString( c.getColumnIndex("ID")));
            map.put("content", c.getString( c.getColumnIndex("content")));
            c.moveToNext();
        }
        return map;
    }
}
