package com.project.samuliak.psychogram.Activity.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.project.samuliak.psychogram.Activity.main.menu.client_menu_items.JournalActivity;
import com.project.samuliak.psychogram.Activity.main.menu.client_menu_items.OnlineActivity;
import com.project.samuliak.psychogram.Activity.main.menu.common_items.DialogActivity;
import com.project.samuliak.psychogram.Adapter.MenuAdapter;
import com.project.samuliak.psychogram.Listener.RecyclerClickListener;
import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.R;

public class MainClientActivity extends AppCompatActivity {

    private Client client;
    private RecyclerView menuRV;
    private String[] items = new String[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_client);
        initActivity();
    }

    private void initActivity() {
        client = getIntent().getExtras().getParcelable(Client.class.getCanonicalName());
        initItems();

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
//                                i = new Intent(view.getContext(), MyClientsActivity.class);
//                                i.putExtra(Psychogolist.class.getCanonicalName(), doctor);
//                                startActivity(i);
                                break;
                            case 1:
                                i = new Intent(view.getContext(), OnlineActivity.class);
                                i.putExtra(Client.class.getCanonicalName(), client);
                                startActivity(i);
                                break;
                            case 2:
                                i = new Intent(view.getContext(), JournalActivity.class);
                                i.putExtra(Client.class.getCanonicalName(), client);
                                startActivity(i);
                                break;
                            case 3:
                                /*
                                Сделать легко. Создать модель анкеты и заполнить её данными.
                                Основная загвоздка состоит в том, что пока что не ясно
                                какой тип активности использовать для этого.
                                 */
                                break;
                            case 4:
                                break;
                            case 5:
                                i = new Intent(view.getContext(), DialogActivity.class);
                                i.putExtra(Client.class.getCanonicalName(), client);
                                startActivity(i);
                                break;
                            case 6:
                                break;
                        }
                    }
                }));
    }

    private void initItems() {
        items[0] = getResources().getString(R.string.find_doctor);
        items[1] = getResources().getString(R.string.who_is_online);
        items[2] = getResources().getString(R.string.everybook);
        items[3] = getResources().getString(R.string.anketa);
        items[4] = getResources().getString(R.string.doctor_rules);
        items[5] = getResources().getString(R.string.online_help);
        items[6] = getResources().getString(R.string.settings);
        menuRV = (RecyclerView) findViewById(R.id.menuRV);
    }
}
