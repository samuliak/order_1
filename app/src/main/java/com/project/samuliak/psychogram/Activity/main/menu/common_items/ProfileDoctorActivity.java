package com.project.samuliak.psychogram.Activity.main.menu.common_items;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.project.samuliak.psychogram.API.PsychogolistAPI;
import com.project.samuliak.psychogram.Activity.main.menu.client_menu_items.DiagnostikMenuActivity;
import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.Model.Psychologist;
import com.project.samuliak.psychogram.Model.Tab;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileDoctorActivity extends AppCompatActivity {

    private Psychologist doctor;
    private Client client;
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
        Button btn_psyh = (Button) findViewById(R.id.btn_psych);
        assert btnSend != null;
        assert btn_psyh != null;
        if (data != null) {
            doctor = data.getParcelable(Psychologist.class.getCanonicalName());
            client = data.getParcelable(Client.class.getCanonicalName());
            if (doctor != null){
                initUI();
            }
            isClientLook = data.getBoolean("IS_CLIENT_LOOK");
            isOwnProfile = data.getBoolean("IS_OWN_ACCOUNT");
            if (isClientLook){
                btnSend.setClickable(true);
                btnSend.setVisibility(View.VISIBLE);
                btn_psyh.setClickable(true);
                btn_psyh.setVisibility(View.VISIBLE);
            }
        }
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("samuliak", "Doctor.login > " +doctor.getLogin());
                Log.e("samuliak", "Client.login > " +client.getLogin());
                final PsychogolistAPI service = Utils.getRetrofit().create(PsychogolistAPI.class);
                Tab tab = new Tab(doctor.getLogin(), client.getLogin(),
                        doctor.getSurname()+" "+doctor.getName(),
                        client.getSurname()+" "+client.getName());
                Call<Void> callSave = service.addTab(tab);
                callSave.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            Call<Tab> callTab = service.getTabByLogins(client.getLogin(), doctor.getLogin());
                            callTab.enqueue(new Callback<Tab>() {
                                @Override
                                public void onResponse(Call<Tab> call, Response<Tab> response) {
                                    if (response.isSuccessful()){
                                        Intent i = new Intent(getBaseContext(), CommunicationActivity.class);
                                        i.putExtra(Tab.class.getCanonicalName(), response.body());
                                        i.putExtra("is_doctor", false);
                                        startActivity(i);
                                    }
                                    Log.e("samuliak", "not succesful > " + response.message());
                                }

                                @Override
                                public void onFailure(Call<Tab> call, Throwable t) {
                                    Toast.makeText(getBaseContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getBaseContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

        btn_psyh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), DiagnostikMenuActivity.class);
                i.putExtra(Client.class.getCanonicalName(), client);
                startActivity(i);
            }
        });
    }

    private void initUI() {
        /*
        Берем разные поля с разметки и вставляем туда данные
         */
    }
}
