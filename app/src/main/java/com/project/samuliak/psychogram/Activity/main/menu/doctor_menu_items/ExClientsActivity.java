package com.project.samuliak.psychogram.Activity.main.menu.doctor_menu_items;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.project.samuliak.psychogram.API.PsychogolistAPI;
import com.project.samuliak.psychogram.Adapter.MyClientsAdapter;
import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.Model.Psychologist;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExClientsActivity extends AppCompatActivity {

    private Psychologist doctor;
    private RecyclerView rv_ex;
    private PsychogolistAPI service;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_clients);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initUI();
    }

    private void initUI() {
        service = Utils.getRetrofit().create(PsychogolistAPI.class);
        progressDialog = new ProgressDialog(this);
        doctor = getIntent().getExtras().getParcelable(Psychologist.class.getCanonicalName());
        rv_ex = (RecyclerView) findViewById(R.id.rv_ex_clients);
        getExClients();
    }

    private void getExClients() {
        Call<List<Client>> listCurrentCall = service.getExClients(doctor.getLogin());
        initProgressDialog();
        listCurrentCall.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if (response.isSuccessful()){
                    MyClientsAdapter adapter = new MyClientsAdapter(getBaseContext(), response.body(),
                            false, true, doctor.getLogin());
                    rv_ex.setAdapter(adapter);
                    rv_ex.setLayoutManager(new LinearLayoutManager(getBaseContext()));
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

    private void initProgressDialog() {
        progressDialog.setMessage(getResources().getString(R.string.connecting_to_server));
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }
}
