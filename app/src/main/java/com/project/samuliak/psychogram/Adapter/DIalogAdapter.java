package com.project.samuliak.psychogram.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.samuliak.psychogram.Model.Tab;
import com.project.samuliak.psychogram.R;

import java.util.List;

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.ViewHolder>{

    private List<Tab> list;
    private boolean isDoctor;

    public DialogAdapter(List<Tab> body, boolean isDoctor) {
        this.list = body;
        this.isDoctor = isDoctor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_dialog, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        if (isDoctor)
            vh.name.setText(list.get(position).getFull_client());
        else
            vh.name.setText(list.get(position).getFull_doctor());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_name);
            icon = (ImageView) itemView.findViewById(R.id.item_icon);


        }
    }
}