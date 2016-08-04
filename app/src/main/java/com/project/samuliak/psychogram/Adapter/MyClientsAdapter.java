package com.project.samuliak.psychogram.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.samuliak.psychogram.API.PsychogolistAPI;
import com.project.samuliak.psychogram.Activity.main.menu.common_items.ProfileClientActivity;
import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Utils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyClientsAdapter extends RecyclerView.Adapter<MyClientsAdapter.ViewHolder> {
    private List<Client> list;
    private String doctor_login;
    private Context context;
    private boolean is_potencial_list;
    private boolean ex;
    private PsychogolistAPI service;

    public MyClientsAdapter(Context context, List<Client> list, boolean is_potencial_list, String doctor) {
        this.context = context;
        this.list = list;
        this.is_potencial_list = is_potencial_list;
        this.ex = false;
        this.doctor_login = doctor;
        service = Utils.getRetrofit().create(PsychogolistAPI.class);
    }

    public MyClientsAdapter(Context context, List<Client> list, boolean is_potencial_list, boolean ex,
                            String doctor) {
        this.context = context;
        this.list = list;
        this.is_potencial_list = is_potencial_list;
        this.ex = ex;
        this.doctor_login = doctor;
        service = Utils.getRetrofit().create(PsychogolistAPI.class);
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
    public void onBindViewHolder(final ViewHolder vh, final int position) {
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

        vh.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ProfileClientActivity.class);
                i.putExtra(Client.class.getCanonicalName(), list.get(position));
                i.putExtra("doctor", doctor_login);
                v.getContext().startActivity(i);
            }
        });

        if (is_potencial_list) {
            vh.btnAgree.setVisibility(View.VISIBLE);
            vh.btnAgree.setClickable(true);
        }
        if(!ex){
            vh.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Call<Void> deleteClient = service.removeClient(list.get(position).getLogin());
                    deleteClient.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                vh.btnDelete.setText(R.string.deleted);
                                vh.btnDelete.setBackgroundResource(R.drawable.btn_delete);
                                list.remove(list.get(position));
                                notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(context, "Ошибка при соединении!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });

            vh.btnAgree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Call<Void> agreeClient = service.agreeClient(list.get(position).getLogin());
                    agreeClient.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            vh.btnAgree.setText(R.string.agreed);
                            vh.btnAgree.setBackgroundResource(R.drawable.btn_agree);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(context, "Ошибка при соединении!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        } else{
            vh.btnDelete.setVisibility(View.INVISIBLE);
            vh.btnDelete.setClickable(false);
        }




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
