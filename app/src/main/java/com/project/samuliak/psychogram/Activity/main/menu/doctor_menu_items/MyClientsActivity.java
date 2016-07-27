package com.project.samuliak.psychogram.Activity.main.menu.doctor_menu_items;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.project.samuliak.psychogram.API.PsychogolistAPI;
import com.project.samuliak.psychogram.Adapter.MyClientsAdapter;
import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.Model.Psychogolist;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Constants;
import com.project.samuliak.psychogram.Util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyClientsActivity extends AppCompatActivity {

    private Psychogolist doctor;
    private RecyclerView list_clients;
    private RadioButton rbCurrent, rbMay;
    private PsychogolistAPI service;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_clients);
        initUI();

        getCurrentClients();

    }

    private void initUI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(PsychogolistAPI.class);
        progressDialog = new ProgressDialog(this);
        Utils.initProgressDialog(progressDialog, this);
        doctor = getIntent().getExtras().getParcelable(Psychogolist.class.getCanonicalName());
        list_clients = (RecyclerView) findViewById(R.id.list_clients);
        rbCurrent = (RadioButton) findViewById(R.id.rbCurrent);
        rbMay = (RadioButton) findViewById(R.id.rbMayClient);
        rbMay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_clients.removeAllViews();
                list_clients.setAdapter(null);
                getMayClients();
            }
        });
        rbCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list_clients.removeAllViews();
                list_clients.setAdapter(null);
                getCurrentClients();
            }
        });
    }



    private void getMayClients() {
        Call<List<Client>> listMayCall = service.getPotencialClients(doctor.getLogin());
        Utils.initProgressDialog(progressDialog, this);
        listMayCall.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if (response.isSuccessful()){
                    MyClientsAdapter adapter = new MyClientsAdapter(getBaseContext(), response.body(),
                            true);
                    list_clients.setAdapter(adapter);
                    list_clients.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Toast.makeText(getBaseContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    private void getCurrentClients() {
        Call<List<Client>> listCurrentCall = service.getClientsByDoctorLogin(doctor.getLogin());
        Utils.initProgressDialog(progressDialog, this);
        listCurrentCall.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if (response.isSuccessful()){
                    MyClientsAdapter adapter = new MyClientsAdapter(getBaseContext(), response.body(),
                            false);
                    list_clients.setAdapter(adapter);
                    list_clients.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Toast.makeText(getBaseContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }


}
