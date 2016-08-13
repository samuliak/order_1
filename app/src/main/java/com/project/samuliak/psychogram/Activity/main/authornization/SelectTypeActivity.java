package com.project.samuliak.psychogram.Activity.main.authornization;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.project.samuliak.psychogram.Activity.main.registration.RegistrationClientActivity;
import com.project.samuliak.psychogram.Activity.main.registration.RegistrationDoctorActivity;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Constants;
import com.project.samuliak.psychogram.Util.TextViewPlus;
import com.vk.sdk.util.VKUtil;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SelectTypeActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean hasRegistred;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);

        YoYo.with(Techniques.SlideInDown).duration(1500).playOn(findViewById(R.id.text1));
        YoYo.with(Techniques.FadeIn).duration(3500).playOn(findViewById(R.id.text2));
        YoYo.with(Techniques.FadeIn).duration(3500).playOn(findViewById(R.id.doctor_text));
        YoYo.with(Techniques.FadeIn).duration(3500).playOn(findViewById(R.id.linear_client));

        TextViewPlus doctor_text = (TextViewPlus) findViewById(R.id.doctor_text);
        RelativeLayout rvClient = (RelativeLayout) findViewById(R.id.linear_client);

        sp = getSharedPreferences(Constants.APP_PREFERENCES,
                Context.MODE_PRIVATE);

        hasRegistred = sp.getBoolean("hasRegistred", false);

        if (!hasRegistred) {
            TextViewPlus text = (TextViewPlus) findViewById(R.id.text2);
            assert text != null;
            assert doctor_text != null;
            text.setVisibility(View.VISIBLE);
            doctor_text.setVisibility(View.VISIBLE);
        } else{
            TextViewPlus textViewPlus = (TextViewPlus) findViewById(R.id.text_in_btn);
            assert textViewPlus != null;
            textViewPlus.setText(R.string.sign_in);
        }

        assert doctor_text != null;
        assert rvClient != null;
        doctor_text.setOnClickListener(this);
        rvClient.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.doctor_text)
            intent = new Intent(v.getContext(), RegistrationDoctorActivity.class);
        else if (v.getId() == R.id.linear_client) {
            if (!hasRegistred) {
                SharedPreferences.Editor e = sp.edit();
                e.putBoolean("hasRegistred", true);
                e.apply();
                intent = new Intent(v.getContext(), RegistrationClientActivity.class);
            }
            else
                intent = new Intent(v.getContext(), AuthorizationActivity.class);
        }
        startActivity(intent);
    }
}
