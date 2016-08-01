package com.project.samuliak.psychogram.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.samuliak.psychogram.Model.Message;
import com.project.samuliak.psychogram.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

    private List<Message> list;
    private Context context;
    private SimpleDateFormat fmt;

    public MessageAdapter(Context context, List<Message> body) {
        this.context = context;
        this.list = body;
        this.fmt = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_message, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        vh.text.setText(list.get(position).getText());
        vh.date.setText(fmt.format(list.get(position).getCreationDate()));
        vh.sender.setText(context.getResources().getString(R.string.sender)+" "+list.get(position).getFull_sender());
        vh.position = position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView text, date, sender;
        public int position;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.mes_text);
            date = (TextView) itemView.findViewById(R.id.mes_date);
            sender = (TextView) itemView.findViewById(R.id.mes_sender);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.e("samuliak", "position > "+position);
                    return false;
                }
            });
        }
    }
}
