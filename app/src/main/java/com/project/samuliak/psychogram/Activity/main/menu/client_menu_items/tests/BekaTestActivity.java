package com.project.samuliak.psychogram.Activity.main.menu.client_menu_items.tests;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.project.samuliak.psychogram.Adapter.BekaAdapter;
import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BekaTestActivity extends AppCompatActivity {

    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beka_test);

        initAct();
    }

    private void initAct() {
        client = getIntent().getExtras().getParcelable(Client.class.getCanonicalName());
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_beka);
        assert rv != null;
        String[] array = getResources().getStringArray(R.array.beka_test);
        BekaAdapter adapter = new BekaAdapter(this, initList(array));
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }

    private List<List<String>> initList(String[] array) {
        int count = 0;
        List<List<String>> list = new ArrayList<>();
        List<String> str = new ArrayList<>();
        int kk = 0;
        for(int i = 0; i < array.length; i++){
            str.add(array[i]);
            if (kk == 3) {
                list.add(str);
                str = new ArrayList<>();
                kk = 0;
                count++;
            }else
                kk ++;
        }
        return list;
    }
}
