package com.project.samuliak.psychogram.Adapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.samuliak.psychogram.Model.Journal;
import com.project.samuliak.psychogram.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class JournalAdapter  extends RecyclerView.Adapter<JournalAdapter.ViewHolder>{

    private List<Journal> list;
    private Context context;
    private SimpleDateFormat fmt;
    private SimpleDateFormat dd;

    public JournalAdapter(Context context, List<Journal> body) {
        this.context = context;
        this.list = body;
        this.fmt = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        this.dd = new SimpleDateFormat("dd.MM.yyyy");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_journal, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        if(position > 0) {
            String str_date = dd.format(list.get(position - 1).getCreationDate());
            String str_date_to = dd.format(list.get(position).getCreationDate());
            if (Integer.parseInt(str_date.substring(0, 2)) != Integer.parseInt(str_date_to.substring(0, 2))) {

                TextView tv = new TextView(context);
                tv.setTextSize(25);
                tv.setTextColor(context.getResources().getColor(R.color.cardview_dark_background));
                tv.setLayoutParams(vh.params);
                tv.setText(dd.format(list.get(position).getCreationDate()));
                vh.conteiner.addView(tv);
            }
        } else{
            TextView tv = new TextView(context);
            tv.setTextSize(25);
            tv.setTextColor(context.getResources().getColor(R.color.cardview_dark_background));
            tv.setLayoutParams(vh.params);
            tv.setText(dd.format(list.get(position).getCreationDate()));
            vh.conteiner.addView(tv);
        }
        vh.note.setText(list.get(position).getNote());
        vh.date.setText(fmt.format(list.get(position).getCreationDate()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView note, date;
        public CardView conteiner;
        public LinearLayout.LayoutParams params;

        public ViewHolder(View itemView) {
            super(itemView);
            note = (TextView) itemView.findViewById(R.id.note);
            date = (TextView) itemView.findViewById(R.id.date);
            conteiner = (CardView) itemView.findViewById(R.id.card);
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            params.setMargins(20, 20, 20, 20);
        }
    }
}
