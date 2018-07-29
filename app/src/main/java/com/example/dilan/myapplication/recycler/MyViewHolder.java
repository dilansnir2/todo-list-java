package com.example.dilan.myapplication.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dilan.myapplication.R;

public class MyViewHolder extends   RecyclerView.ViewHolder{

    private TextView textView;
    private ImageView imageView;

    public MyViewHolder(View itemView){
        super(itemView);
        textView = itemView.findViewById(R.id.text);
    }

    public void bind(final MyObject myObject, final MyAdapter.OnItemClickListener listener){
        textView.setText(myObject.getText());
        /*Picasso.with(imageView.getContext()).load(myobject.getImageUrl());*/
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(myObject);
            }
        });
    }

}
