package com.project.samuliak.psychogram.Activity.main.menu.common_items;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.project.samuliak.psychogram.API.PsychogolistAPI;
import com.project.samuliak.psychogram.Adapter.MessageAdapter;
import com.project.samuliak.psychogram.Model.Message;
import com.project.samuliak.psychogram.Model.Tab;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Utils;
import com.squareup.okhttp.internal.Util;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunicationActivity extends AppCompatActivity {

    private boolean isDoctor;
    private List<Message> list;
    private MessageAdapter adapter;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);

        initList();
    }

    private void initList() {
        final Tab tab = getIntent().getExtras().getParcelable(Tab.class.getCanonicalName());
        isDoctor = getIntent().getExtras().getBoolean("is_doctor");
        container = (LinearLayout) findViewById(R.id.error_container);
        final LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.message_is_empty, null);

        final RecyclerView rv_mes = (RecyclerView) findViewById(R.id.rv_mes);
        final TextView textView = (TextView) findViewById(R.id.text_mes);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        Utils.initProgressDialog(progressDialog, this);
        Button btnSend = (Button) findViewById(R.id.btn_send);
        final PsychogolistAPI service = Utils.getRetrofit().create(PsychogolistAPI.class);
        assert tab != null;
        Call<List<Message>> listCall = service.getAllMessageByTab(tab.getId());
        listCall.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful()){
                    if (response.body().size() == 0) {
                        assert container != null;
                        container.addView(view);
                    }
                    list = response.body();
                    adapter = new MessageAdapter(getBaseContext(), list);
                    assert rv_mes != null;
                    rv_mes.setAdapter(adapter);
                    rv_mes.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    setListenerOnServer();
                }
                Log.e("samuliak", "response.message > "+response.message());
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Log.e("samuliak", "Error > "+t.toString());
                Toast.makeText(getBaseContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });

        assert btnSend != null;
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert textView != null;
                textView.setError(null);
                final String text = textView.getText().toString();
                if (text.isEmpty()) {
                    YoYo.with(Techniques.Shake).duration(800).playOn(textView);
                    textView.setError(getBaseContext().getResources().getString(R.string.fill_the_field));
                    return;
                }
                assert container != null;
                container.removeView(view);
                Call<Void> call = null;
                Message local_mes = null;
                if (isDoctor) {
                    local_mes = new Message(text, new Date(), tab.getId(), tab.getDoctor(), tab.getFull_doctor());
                    call = service.addMessage(text, tab.getDoctor(), tab.getFull_doctor(), tab.getId());
                }
                else {
                    local_mes = new Message(text, new Date(), tab.getId(), tab.getClient(), tab.getFull_client());
                    call = service.addMessage(text, tab.getClient(), tab.getFull_client(), tab.getId());
                }
                Utils.initProgressDialog(progressDialog, getBaseContext());
                final Message finalLocal_mes = local_mes;
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            textView.setText("");
                            list.add(finalLocal_mes);
                            adapter.notifyDataSetChanged();
                            Log.e("samuliak", "succesful!");
                        } else
                            Log.e("samuliak", "not succesful!");
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("samuliak", "onFailure! > "+t.toString());
                        Toast.makeText(getBaseContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });
            }
        });

    }

    private void setListenerOnServer() {
        thread.start();
    }


    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            final PsychogolistAPI service = Utils.getRetrofit().create(PsychogolistAPI.class);
            while(true) {
                assert list != null;
                if (list != null && list.size() > 0) {
                    Message last = list.get(list.size() - 1);
                    Call<List<Message>> call = service.getUpdateMessage(last.getTabId(),
                            last.getText());
                    call.enqueue(new Callback<List<Message>>() {
                        @Override
                        public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                            if (response.isSuccessful()) {
                                Log.e("samuliak", "succesful > " + response.body().size());
                                list.addAll(response.body());
                                adapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Message>> call, Throwable t) {
                            Log.e("samuliak", "onFailure > " + t.toString());
                        }
                    });

                    try {
                        Thread.sleep(15000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    });
}
