package com.example.dilan.myapplication.recycler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dilan.myapplication.EditActivity;
import com.example.dilan.myapplication.MainActivity;
import com.example.dilan.myapplication.R;
import com.example.dilan.myapplication.task.Task;
import com.example.dilan.myapplication.task.TasksBDD;

import net.steamcrafted.materialiconlib.MaterialIconView;

//Integer.parseInt
public class MyViewHolder extends   RecyclerView.ViewHolder{

    private TextView textView;
    private TextView dateView;
    private MaterialIconView deleteIcon;
    private MaterialIconView editIcon;
    private RecyclerView recyclerView;

    public MyViewHolder(View itemView){
        super(itemView);
        textView = itemView.findViewById(R.id.text);
        dateView = itemView.findViewById(R.id.dateView);
        deleteIcon = itemView.findViewById(R.id.iconDelete);
        editIcon = itemView.findViewById(R.id.iconEdit);
    }

    public void bind(final MyObject myObject, final MyAdapter.OnItemClickListener listener,  final MainActivity self){
        textView.setText(myObject.getText());
        dateView.setText(myObject.getDate());
        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(myObject);
                TasksBDD tasksBDD = new TasksBDD(self);
                tasksBDD.open();
                tasksBDD.removeTaskWithID(Integer.parseInt(myObject.getId()));
                recyclerView = (RecyclerView) self.findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(self));
                recyclerView.setAdapter(new MyAdapter(Task.showTasks(self), new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(MyObject object) {
                        Log.d("ok",object.getText());
                    }
                }, self));
            }
        });
        editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(myObject);
                Intent intent = new Intent(self, EditActivity.class);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(myObject.getId()));
                intent.putExtras(b);
                self.startActivity(intent);
            }
        });
    }

}
