package com.project.samuliak.psychogram.Activity.main.menu.client_menu_items;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.samuliak.psychogram.API.PsychogolistAPI;
import com.project.samuliak.psychogram.Adapter.OnlineAdapter;
import com.project.samuliak.psychogram.Model.Psychologist;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Utils;
import com.transitionseverywhere.AutoTransition;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.Recolor;
import com.transitionseverywhere.Scene;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindActivity extends AppCompatActivity {

    private ViewGroup container;
    private FrameLayout container_empty;
    private boolean visible;

    private PsychogolistAPI service;
    private ProgressDialog progressDialog;
    private RecyclerView rv_simple;
    private OnlineAdapter adapter;
    private List<Psychologist> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        initAct();
    }

    private void initAct() {
        service = Utils.getRetrofit().create(PsychogolistAPI.class);
        progressDialog = new ProgressDialog(this);
        list = new ArrayList<>();
        rv_simple = (RecyclerView) findViewById(R.id.rv_simple_doctor);
        container_empty = (FrameLayout) findViewById(R.id.container_empty);
        Utils.initProgressDialog(progressDialog, this);
        Call<List<Psychologist>> call = service.getRandomPsychologist();
        call.enqueue(new Callback<List<Psychologist>>() {
            @Override
            public void onResponse(Call<List<Psychologist>> call, Response<List<Psychologist>> response) {
                if(response.isSuccessful()) {
                    list = response.body();
                    adapter = new OnlineAdapter(getBaseContext(), list, true);
                    rv_simple.setAdapter(adapter);
                    rv_simple.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Psychologist>> call, Throwable t) {
                Toast.makeText(getBaseContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
        searchBlok();
    }

    private void searchBlok() {
        container = (ViewGroup) findViewById(R.id.container);
        final Scene scene2 = Scene.getSceneForLayout(container, R.layout.find, this);

        final Button btn_start = (Button) findViewById(R.id.open_block);

        visible = false;
        assert btn_start != null;
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!visible) {
                    TransitionSet set = new TransitionSet();
                    set.addTransition(new ChangeBounds());
                    set.setOrdering(TransitionSet.ORDERING_TOGETHER);
                    set.setDuration(500);
                    set.setInterpolator(new AccelerateInterpolator());
                    TransitionManager.go(scene2, set);
                    visible = true;
                    connectToServer();
                } else {
                    container.removeAllViews();
                    visible = false;
                }
                TransitionManager.beginDelayedTransition(container, new Recolor());
                btn_start.setBackgroundDrawable(
                        new ColorDrawable(getResources().getColor(!visible ? R.color.colorPrimary :
                                R.color.colorAccent)));
            }

            private void connectToServer() {
                Button start_find = (Button) scene2.getSceneRoot().findViewById(R.id.start_find);
                start_find.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String country = ((EditText) scene2.getSceneRoot().findViewById(R.id.country)).getText().toString();
                        String city = ((EditText) scene2.getSceneRoot().findViewById(R.id.city)).getText().toString();
                        String competence = ((EditText) scene2.getSceneRoot().findViewById(R.id.competence)).getText().toString();
                        Utils.initProgressDialog(progressDialog, v.getContext());
                        Call<List<Psychologist>> call = service.getAllPsychologistByParameters(country, city, competence);
                        call.enqueue(new Callback<List<Psychologist>>() {
                            @Override
                            public void onResponse(Call<List<Psychologist>> call, Response<List<Psychologist>> response) {
                                if (response.isSuccessful()){
                                    rv_simple.removeAllViews();
                                    if(response.body().size() > 0) {
                                        rv_simple.removeAllViews();
                                        container_empty.removeAllViews();
                                        list = response.body();
                                        adapter = new OnlineAdapter(getBaseContext(), list, true);
                                        rv_simple.setAdapter(adapter);
                                        rv_simple.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                                    } else {
                                        list.clear();
                                        adapter.notifyDataSetChanged();
                                        rv_simple.removeAllViews();
                                        TextView tv = new TextView(getBaseContext());
                                        tv.setText("Поиск не дал результатов");
                                        tv.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                                        tv.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
                                        container_empty.addView(tv);
                                    }
                                }
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<List<Psychologist>> call, Throwable t) {
                                Toast.makeText(getBaseContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        });
                    }
                });
            }
        });
    }
}
