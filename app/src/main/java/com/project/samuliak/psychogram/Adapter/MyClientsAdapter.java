package com.project.samuliak.psychogram.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Constants;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyClientsAdapter extends RecyclerView.Adapter<MyClientsAdapter.ViewHolder> {
    private List<Client> list;
    private Context context;
    private boolean CODE;


    public MyClientsAdapter(Context context, List<Client> list, boolean CODE) {
        this.context = context;
        this.list = list;
        this.CODE = CODE;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView icon;
        public TextView name;
        public TextView place_of_live;
        public TextView interest;
        public Button btnProfile, btnDelete, btnAgree;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (CircleImageView) itemView.findViewById(R.id.item_icon);
            name = (TextView) itemView.findViewById(R.id.item_name);
            place_of_live = (TextView) itemView.findViewById(R.id.item_place_of_live);
            interest = (TextView) itemView.findViewById(R.id.item_interest);
            btnProfile = (Button) itemView.findViewById(R.id.btnProfile);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
            btnAgree = (Button) itemView.findViewById(R.id.btnAgree);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_client, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Client client = list.get(position);
        StringBuilder str = new StringBuilder();
        str.append(client.getSurname()).append(" ");
        str.append(client.getName()).append(",     ");
        if (client.getAge().toString().length() > 0)
            str.append(String.valueOf(client.getAge()));
        vh.icon.setImageResource(R.drawable.client);
        vh.name.setText(str);
        vh.place_of_live.setText(client.getCountry()+", "+client.getCity());
        vh.interest.setText(client.getInterest());

//        if (CODE)
//            vh.btnAgree.setVisibility(View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
