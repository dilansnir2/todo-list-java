package com.example.dilan.myapplication.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dilan.myapplication.MainActivity;
import com.example.dilan.myapplication.R;
import com.example.dilan.myapplication.recycler.MyObject;
import com.example.dilan.myapplication.recycler.MyViewHolder;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<MyObject> list;

    private final OnItemClickListener listener;
    private final MainActivity self;

    public MyAdapter(List<MyObject> list, OnItemClickListener listener, final MainActivity self) {
        this.list = list;
        this.listener = listener;
        this.self = self;
    }

    public interface OnItemClickListener {
        void onItemClick(MyObject object);
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_cards,viewGroup,false);
        return new MyViewHolder(view);
    }


    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.bind(list.get(position), listener, this.self);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
