package com.project.samuliak.psychogram.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.samuliak.psychogram.Activity.main.menu.common_items.ProfileDoctorActivity;
import com.project.samuliak.psychogram.Model.Psychologist;
import com.project.samuliak.psychogram.R;

import java.util.List;

public class OnlineAdapter extends RecyclerView.Adapter<OnlineAdapter.ViewHolder>{

    private List<Psychologist> list;
    private Context context;
    private boolean is_find;

    public OnlineAdapter(Context context, List<Psychologist> list){
        this.context = context;
        this.list = list;
        is_find = false;
    }

    public OnlineAdapter(Context context, List<Psychologist> list, boolean find){
        this.context = context;
        this.list = list;
        is_find = find;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_online_doctor, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        vh.text.setText(list.get(position).getName()+" "+list.get(position).getSurname());
        if(is_find)
            vh.indicator.setVisibility(View.INVISIBLE);

        vh.btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProfileDoctorActivity.class);
                i.putExtra("IS_CLIENT_LOOK", true);
                i.putExtra("IS_OWN_ACCOUNT", false);
                i.putExtra(Psychologist.class.getCanonicalName(), list.get(position));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView text;
        public ImageView indicator;
        public Button btn_profile;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.on_text);
            indicator = (ImageView)itemView.findViewById(R.id.indicator);
            btn_profile = (Button) itemView.findViewById(R.id.btn_profile);
        }
    }

}