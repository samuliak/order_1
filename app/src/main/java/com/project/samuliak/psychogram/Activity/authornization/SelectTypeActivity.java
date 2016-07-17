package com.project.samuliak.psychogram.Activity.authornization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.samuliak.psychogram.Activity.registration.RegistrationClientActivity;
import com.project.samuliak.psychogram.Activity.registration.RegistrationDoctorActivity;
import com.project.samuliak.psychogram.R;

public class SelectTypeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);
        TextView tv_registr = (TextView) findViewById(R.id.tv_registr);
        RelativeLayout rvDoctor = (RelativeLayout) findViewById(R.id.rvDoctor);
        RelativeLayout rvClient = (RelativeLayout) findViewById(R.id.rvClient);
        assert rvDoctor != null;
        assert rvClient != null;
        assert tv_registr != null;
        rvDoctor.setOnClickListener(this);
        rvClient.setOnClickListener(this);
        tv_registr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                YoYo.with(Techniques.Pulse).duration(800).playOn(v);
                Intent intent = new Intent(v.getContext(), AuthorizationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.rvDoctor)
            intent = new Intent(v.getContext(), RegistrationDoctorActivity.class);
        else if (v.getId() == R.id.rvClient)
            intent = new Intent(v.getContext(), RegistrationClientActivity.class);
        startActivity(intent);
    }
}
