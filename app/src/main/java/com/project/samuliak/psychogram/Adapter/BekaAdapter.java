package com.project.samuliak.psychogram.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.project.samuliak.psychogram.R;

import java.util.ArrayList;
import java.util.List;

public class BekaAdapter extends RecyclerView.Adapter<BekaAdapter.ViewHolder> {
    private Context context;
    private List<List<String>> list;

    private List<Integer> answer;

    public BekaAdapter(Context context, List<List<String>> list) {
        this.context = context;
        this.list = list;
        this.answer = new ArrayList<>();
        for (int i=0; i<22; i++) this.answer.add(null);
        Log.e("samuliak", "answer.size > "+answer.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_beka, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder vh, int position) {
        List<String> str = list.get(position);
        vh.pos = position;
        if (position != 20) {
            vh.number.setText(vh.con + (position+1));
            vh.rb0.setText(str.get(0));
            vh.rb1.setText(str.get(1));
            vh.rb2.setText(str.get(2));
            vh.rb3.setText(str.get(3));
            vh.rb2.setVisibility(View.VISIBLE);
            vh.rb2.setClickable(true);
            vh.rb3.setVisibility(View.VISIBLE);
            vh.rb3.setClickable(true);
        } else {
            vh.number.setText(vh.con + (position+1) +": "+ str.get(0));
            vh.rb0.setText(context.getResources().getString(R.string.yes));
            vh.rb1.setText(context.getResources().getString(R.string.no));
            vh.rb2.setVisibility(View.INVISIBLE);
            vh.rb2.setClickable(false);
            vh.rb3.setVisibility(View.INVISIBLE);
            vh.rb3.setClickable(false);
            Button btn = new Button(context);
            btn.setText(context.getResources().getString(R.string.send));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i=0; i<answer.size(); i++){
                        Log.e("samuliak", "i > "+i+"; ans > "+answer.get(i));
                    }
                    if (!answer.contains(null)){
                        /*
                        Вивести повідомлення за проходження тесту. Надіслати результати на сервер
                        і закрити дану активність
                         */
                    }else {
                        Toast.makeText(context, context.getResources().getString(R.string.answer_all_quest),
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
            vh.container.addView(btn);
        }
        vh.rb_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                int checkedIndex = group.indexOfChild(checkedRadioButton);
                answer.add(vh.pos, checkedIndex);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView number;
        public LinearLayout container;
        public RadioButton rb0, rb1, rb2, rb3;
        public RadioGroup rb_group;
        public String con;

        public int pos;

        public ViewHolder(View itemView) {
            super(itemView);
            number = (TextView) itemView.findViewById(R.id.number);
            container = (LinearLayout) itemView.findViewById(R.id.container);
            rb0 = (RadioButton) itemView.findViewById(R.id.rb0);
            rb_group = (RadioGroup) itemView.findViewById(R.id.rb_group);
            rb1 = (RadioButton) itemView.findViewById(R.id.rb1);
            rb2 = (RadioButton) itemView.findViewById(R.id.rb2);
            rb3 = (RadioButton) itemView.findViewById(R.id.rb3);
            con = number.getText().toString();
        }
    }

}
