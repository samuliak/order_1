package com.project.samuliak.psychogram.Activity.main.menu.common_items;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.R;

public class ProfileClientActivity extends AppCompatActivity {

    private Client client;
    private String doctor_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_client);
        initArg();
    }

    private void initArg() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            client = bundle.getParcelable(Client.class.getCanonicalName());
            doctor_login = bundle.getString("doctor");
        }
    }
}
