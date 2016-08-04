package com.project.samuliak.psychogram.Activity.main.menu.common_items;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.project.samuliak.psychogram.Model.Psychologist;
import com.project.samuliak.psychogram.R;

public class ProfileDoctorActivity extends AppCompatActivity {

    private Psychologist doctor;
    private boolean isClientLook, isOwnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_doctor);
        initActivity();

        /*
        Сделать дизайн. Данные есть (переменная doctor), а как красиво оформить не знаю.
        Данный класс будет так же использоватся при просмотре профиля доктора клиентом
        с возможностью написать ему.
         */
    }

    private void initActivity() {
        Bundle data = getIntent().getExtras();

        // Приходять такие данные, как сам доктор и 2 переменные типа булеан.
        // Первая переменная - смотрит ли клиент. И вторая - власная ли это страница
        ImageButton btnSend = (ImageButton) findViewById(R.id.btnSend);
        if (data != null) {
            doctor = data.getParcelable(Psychologist.class.getCanonicalName());
            if (doctor != null){
                initUI();
            }
            isClientLook = data.getBoolean("IS_CLIENT_LOOK");
            isOwnProfile = data.getBoolean("IS_OWN_ACCOUNT");
            if (isClientLook){
                assert btnSend != null;
                btnSend.setClickable(true);
                btnSend.setVisibility(View.VISIBLE);
            }
        }
        assert btnSend != null;
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Отправить сообщение!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initUI() {
        /*
        Берем разные поля с разметки и вставляем туда данные
         */
    }
}
