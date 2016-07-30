package com.project.samuliak.psychogram.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.samuliak.psychogram.API.PsychogolistAPI;
import com.project.samuliak.psychogram.Activity.main.menu.common_items.ProfileDoctorActivity;
import com.project.samuliak.psychogram.Model.Psychogolist;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder>{

    private List<Psychogolist> list;
    private Context context;
    private boolean FRIEND;
    private Psychogolist doctor;

    public FriendsAdapter(Context context, List<Psychogolist> list, boolean FRIEND, Psychogolist doctor){
        this.context = context;
        this.list = list;
        this.FRIEND = FRIEND;
        this.doctor = doctor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_friend, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        vh.text.setText(list.get(position).getName());
        vh.id = position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView text;
        public Button btn_profile, btn_agree, btn_delete;
        private int id;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.fri_text);
            btn_profile = (Button) itemView.findViewById(R.id.btn_profile);
            btn_agree = (Button) itemView.findViewById(R.id.btn_agree);
            btn_delete = (Button) itemView.findViewById(R.id.btn_delete);

            if(!FRIEND){
                btn_agree.setClickable(true);
                btn_agree.setVisibility(View.VISIBLE);
                btn_agree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PsychogolistAPI service = Utils.getRetrofit().create(PsychogolistAPI.class);
                        Call<Void> call = service.agreeFriend(doctor.getLogin(), list.get(id).getLogin());
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()){
                                    btn_agree.setText(context.getResources().getString(R.string.agreed));
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(context, R.string.connecting_error, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }

            btn_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ProfileDoctorActivity.class);
                    i.putExtra("IS_CLIENT_LOOK", false);
                    i.putExtra("IS_OWN_ACCOUNT", false);
                    i.putExtra(Psychogolist.class.getCanonicalName(), list.get(id));
                    context.startActivity(i);
                }
            });

            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PsychogolistAPI service = Utils.getRetrofit().create(PsychogolistAPI.class);
                    Call<Void> call = service.deleteFriend(doctor.getLogin(), list.get(id).getLogin());
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()){
                                btn_delete.setText(context.getResources().getString(R.string.deleted));
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(context, R.string.connecting_error, Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        }
    }

}