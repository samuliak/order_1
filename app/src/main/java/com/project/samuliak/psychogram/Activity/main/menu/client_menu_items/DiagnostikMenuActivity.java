package com.project.samuliak.psychogram.Activity.main.menu.client_menu_items;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.project.samuliak.psychogram.Activity.main.menu.client_menu_items.tests.BekaTestActivity;
import com.project.samuliak.psychogram.Activity.main.menu.client_menu_items.tests.PsychognosisActivity;
import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.R;

public class DiagnostikMenuActivity extends AppCompatActivity {

    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostik_menu);

        client = getIntent().getExtras().getParcelable(Client.class.getCanonicalName());

        initBtn();
    }

    private void initBtn() {
        Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        final Intent i = new Intent(this, PsychognosisActivity.class);

        /*
            Все ссылаются на 1 активити, но толко каждый будеть иметь свои данные (список с вопросами)
         */

        assert btn1 != null;
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
        assert btn2 != null;
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
        assert btn3 != null;
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
        assert btn4 != null;
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
        assert btn5 != null;
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
        assert btn6 != null;
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
        assert btn7 != null;
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
        assert btn8 != null;
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
        assert btn9 != null;
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), BekaTestActivity.class);
                intent.putExtra(Client.class.getCanonicalName(), client);
                startActivity(intent);
            }
        });
    }

}
