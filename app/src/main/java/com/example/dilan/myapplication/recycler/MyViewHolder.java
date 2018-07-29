package com.example.dilan.myapplication.recycler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
    private TextView titleView;
    private TextView dateView;
    private MaterialIconView deleteIcon;
    private MaterialIconView editIcon;
    private RecyclerView recyclerView;
    private MaterialIconView statusIcon;
    private CardView card;

    public MyViewHolder(View itemView){
        super(itemView);
        textView = itemView.findViewById(R.id.textCard);
        titleView = itemView.findViewById(R.id.title);
        dateView = itemView.findViewById(R.id.dateView);
        deleteIcon = itemView.findViewById(R.id.iconDelete);
        editIcon = itemView.findViewById(R.id.iconEdit);
        statusIcon = itemView.findViewById(R.id.iconStatus);
        card = itemView.findViewById(R.id.card);
    }

    public void bind(final MyObject myObject, final MyAdapter.OnItemClickListener listener,  final MainActivity self){

        System.out.println(myObject.getStatus());
        if(!myObject.getStatus()){
            titleView.setText(myObject.getTitle());
            statusIcon.setColor(Color.BLUE);
        }

        if(myObject.getStatus()){
            SpannableString content = new SpannableString(myObject.getTitle());
            content.setSpan(new StrikethroughSpan(), 0, content.length(), 0);
            titleView.setText(content);
        }

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
                recyclerView.setAdapter(new MyAdapter(Task.showTasks(self, "ALL"), new MyAdapter.OnItemClickListener() {
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

        statusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(myObject);
                TasksBDD tasksBDD = new TasksBDD(self);
                tasksBDD.open();
                tasksBDD.updateTask(Integer.parseInt(myObject.getId()), myObject.getText(), myObject.getDate(), !myObject.getStatus(), myObject.getTitle());
                recyclerView = (RecyclerView) self.findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(self));
                recyclerView.setAdapter(new MyAdapter(Task.showTasks(self, "ALL"), new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(MyObject object) {
                        Log.d("ok",object.getText());
                    }
                }, self));
            }
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(textView.getVisibility() == View.VISIBLE) {
                    textView.setVisibility(View.INVISIBLE);
                    textView.setTextSize(0);

                }else{
                    textView.setVisibility(View.VISIBLE);
                    textView.setTextSize(18);

                }
            }
        });
    }

}
