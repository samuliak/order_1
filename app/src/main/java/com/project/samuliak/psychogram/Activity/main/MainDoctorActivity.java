package com.project.samuliak.psychogram.Activity.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.project.samuliak.psychogram.Activity.menu.doctor_menu.ExClientsActivity;
import com.project.samuliak.psychogram.Activity.menu.doctor_menu.MyClientsActivity;
import com.project.samuliak.psychogram.Adapter.MenuAdapter;
import com.project.samuliak.psychogram.Listener.RecyclerClickListener;
import com.project.samuliak.psychogram.Model.Psychogolist;
import com.project.samuliak.psychogram.R;

public class MainDoctorActivity extends AppCompatActivity {

    private Psychogolist doctor;
    private RecyclerView menuRV;
    private String[] items = new String[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_doctor);
        initActivity();
    }

    private void initActivity() {
        doctor = getIntent().getExtras().getParcelable(Psychogolist.class.getCanonicalName());
        initUI();
        MenuAdapter adapter = new MenuAdapter(items);
        menuRV.setAdapter(adapter);
        menuRV.setLayoutManager(new LinearLayoutManager(this));
        menuRV.addOnItemTouchListener(new RecyclerClickListener(this,
                new RecyclerClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent i;
                        switch (position){
                            case 0:
                                i = new Intent(view.getContext(), MyClientsActivity.class);
                                i.putExtra(Psychogolist.class.getCanonicalName(), doctor);
                                startActivity(i);
                                break;
                            case 1:
                                break;
                            case 2:
                                i = new Intent(view.getContext(), ExClientsActivity.class);
                                i.putExtra(Psychogolist.class.getCanonicalName(), doctor);
                                startActivity(i);
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 5:
                                break;
                            case 6:
                                break;
                            case 7:
                                break;
                            case 8:
                                break;
                        }
                    }
                }));
    }

    private void initUI() {
        items[0] = getResources().getString(R.string.my_clients);
        items[1] = getResources().getString(R.string.cabinet);
        items[2] = getResources().getString(R.string.clients_history);
        items[3] = getResources().getString(R.string.psychodiagnostika);
        items[4] = getResources().getString(R.string.online_help);
        items[5] = getResources().getString(R.string.information_about_me);
        items[6] = getResources().getString(R.string.first_help);
        items[7] = getResources().getString(R.string.friends_from_work);
        items[8] = getResources().getString(R.string.settings);
        menuRV = (RecyclerView) findViewById(R.id.menuRV);
    }
}
