package com.project.samuliak.psychogram.Activity.main.menu.client_menu_items;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.project.samuliak.psychogram.API.ClientAPI;
import com.project.samuliak.psychogram.Adapter.JournalAdapter;
import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.Model.Journal;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JournalActivity extends AppCompatActivity {

    private Client client;
    private EditText view_add;
    private FrameLayout container;
    private RecyclerView rv_journals;
    private List<Journal> list;
    private ClientAPI service;
    private JournalAdapter adapter;
    private boolean isCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initAct();
    }

    private void initAct() {
        client = getIntent().getExtras().getParcelable(Client.class.getCanonicalName());
        container = (FrameLayout) findViewById(R.id.container);
        rv_journals = (RecyclerView) findViewById(R.id.rv_journals);
        view_add = new EditText(this);
        isCheck = false;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        service = Utils.getRetrofit().create(ClientAPI.class);


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isCheck) {
                    container.addView(view_add);
                    fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                    isCheck = true;
                } else{
                    Utils.initProgressDialog(progressDialog, getBaseContext());
                    final Journal local_journal = new Journal(client.getLogin(), view_add.getText().toString(), new Date());
                    Call<Void> call = service.saveJournal(view_add.getText().toString(), client.getLogin());
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()){
                                list.add(local_journal);
                                adapter.notifyDataSetChanged();
                            }
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getBaseContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    });
                    view_add.setText("");
                    container.removeView(view_add);
                    fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
                    isCheck = false;
                }

            }
        });
        Utils.initProgressDialog(progressDialog, getBaseContext());
        Call<List<Journal>> listCall = service.getAllJournalsByLogin(client.getLogin());
        listCall.enqueue(new Callback<List<Journal>>() {
            @Override
            public void onResponse(Call<List<Journal>> call, Response<List<Journal>> response) {
                if (response.isSuccessful()){
                    list = response.body();
                    adapter = new JournalAdapter(getBaseContext(), list);
                    rv_journals.setAdapter(adapter);
                    rv_journals.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Journal>> call, Throwable t) {
                Toast.makeText(getBaseContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

}
