package com.project.samuliak.psychogram.Activity.main.menu.client_menu_items;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.project.samuliak.psychogram.API.PsychogolistAPI;
import com.project.samuliak.psychogram.Adapter.OnlineAdapter;
import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.Model.Psychogolist;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnlineActivity extends AppCompatActivity {

    private Client client;
    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        initActivity();
    }

    private void initActivity() {
        client = getIntent().getExtras().getParcelable(Client.class.getCanonicalName());
        rv = (RecyclerView) findViewById(R.id.rv_online);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        Utils.initProgressDialog(progressDialog, this);
        PsychogolistAPI service = Utils.getRetrofit().create(PsychogolistAPI.class);
        Call<List<Psychogolist>> call = service.getAllDoctorWhoIsOnline();
        call.enqueue(new Callback<List<Psychogolist>>() {
            @Override
            public void onResponse(Call<List<Psychogolist>> call, Response<List<Psychogolist>> response) {
                if (response.isSuccessful()){
                    OnlineAdapter adapter = new OnlineAdapter(getBaseContext(), response.body());
                    rv.setAdapter(adapter);
                    rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Psychogolist>> call, Throwable t) {
                Toast.makeText(getBaseContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });

    }
}
