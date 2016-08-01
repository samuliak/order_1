package com.project.samuliak.psychogram.Activity.main.menu.common_items;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.project.samuliak.psychogram.API.PsychogolistAPI;
import com.project.samuliak.psychogram.Adapter.DialogAdapter;
import com.project.samuliak.psychogram.Listener.RecyclerClickListener;
import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.Model.Psychogolist;
import com.project.samuliak.psychogram.Model.Tab;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Constants;
import com.project.samuliak.psychogram.Util.Utils;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DialogActivity extends ActionBarActivity {

    private static ProgressDialog progressDialog;
    private boolean isDoctor;
    private Psychogolist doctor;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initList();
    }

    private void initList() {
        final PsychogolistAPI service = Utils.getRetrofit().create(PsychogolistAPI.class);
        Call<List<Tab>> listCall = null;

        Bundle data = getIntent().getExtras();
        if (data != null) {
            doctor = data.getParcelable(Psychogolist.class.getCanonicalName());
            client = data.getParcelable(Client.class.getCanonicalName());
            if (doctor != null) {
                listCall = service.getAllTabsByDoctor(doctor.getLogin());
                isDoctor = true;
            } else if (client != null) {
                listCall = service.getAllTabsByClient(client.getLogin());
                isDoctor = false;
            }
        }
        progressDialog = new ProgressDialog(this);
        final RecyclerView rv_dialog = (RecyclerView) findViewById(R.id.rv_dialog);
        Utils.initProgressDialog(progressDialog, this);

        assert listCall != null;
        listCall.enqueue(new Callback<List<Tab>>() {
            @Override
            public void onResponse(Call<List<Tab>> call, final Response<List<Tab>> response) {
                if (response.isSuccessful()){
                    DialogAdapter adapter = new DialogAdapter(response.body(), isDoctor);
                    assert rv_dialog != null;
                    rv_dialog.setAdapter(adapter);
                    rv_dialog.setLayoutManager(new LinearLayoutManager(getBaseContext()));

                    rv_dialog.addOnItemTouchListener(new RecyclerClickListener(getBaseContext(),
                            new RecyclerClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Intent i = new Intent(getBaseContext(), CommunicationActivity.class);
                                    i.putExtra(Tab.class.getCanonicalName(), response.body().get(position));
                                    if (doctor != null)
                                        i.putExtra("is_doctor", true);
                                    else if (client != null)
                                        i.putExtra("is_doctor", false);
                                    startActivity(i);
                                }
                            }));


                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Tab>> call, Throwable t) {
                Toast.makeText(getBaseContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

}



