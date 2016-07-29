package com.project.samuliak.psychogram.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.samuliak.psychogram.R;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    public String[] items;

    public MenuAdapter(String[] items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.item_name.setText(items[position]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView item_name;

        public ViewHolder(View itemView) {
            super(itemView);
            item_name = (TextView) itemView.findViewById(R.id.item_name);

        }
    }

}
