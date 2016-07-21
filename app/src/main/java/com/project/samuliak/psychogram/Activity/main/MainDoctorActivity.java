package com.project.samuliak.psychogram.Activity.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.samuliak.psychogram.Model.Psychogolist;
import com.project.samuliak.psychogram.R;

public class MainDoctorActivity extends AppCompatActivity {

    private Psychogolist doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doctor);
        doctor = getIntent().getExtras().getParcelable(Psychogolist.class.getCanonicalName());
    }
}
