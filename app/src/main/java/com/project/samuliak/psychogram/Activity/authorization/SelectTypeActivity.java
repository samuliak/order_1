package com.project.samuliak.psychogram.Activity.authorization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.samuliak.psychogram.R;

public class SelectTypeActivity extends AppCompatActivity {

    private RelativeLayout rvDoctor, rvClient;
    private TextView tv_registr;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);
        tv_registr = (TextView) findViewById(R.id.tv_registr);
        rvDoctor = (RelativeLayout) findViewById(R.id.rvDoctor);
        rvClient = (RelativeLayout) findViewById(R.id.rvClient);
        rvDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                YoYo.with(Techniques.Pulse).duration(700).playOn(v);
                intent = new Intent(v.getContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
        rvClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                YoYo.with(Techniques.Pulse).duration(700).playOn(v);
                intent = new Intent(v.getContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
        tv_registr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                YoYo.with(Techniques.Pulse).duration(800).playOn(v);
                intent = new Intent(v.getContext(), AuthorizationActivity.class);
                startActivity(intent);
            }
        });
    }
}
