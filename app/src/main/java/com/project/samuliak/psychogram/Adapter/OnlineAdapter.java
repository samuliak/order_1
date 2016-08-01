package com.project.samuliak.psychogram.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.samuliak.psychogram.Model.Psychogolist;
import com.project.samuliak.psychogram.R;

import java.util.List;

public class OnlineAdapter extends RecyclerView.Adapter<OnlineAdapter.ViewHolder>{

    private List<Psychogolist> list;
    private Context context;

    public OnlineAdapter(Context context, List<Psychogolist> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_online_doctor, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        vh.text.setText(list.get(position).getName()+" "+list.get(position).getSurname());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.on_text);
        }
    }

}