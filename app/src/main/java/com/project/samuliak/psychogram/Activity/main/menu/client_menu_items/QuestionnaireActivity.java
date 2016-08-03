package com.project.samuliak.psychogram.Activity.main.menu.client_menu_items;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.samuliak.psychogram.API.ClientAPI;
import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.Model.Questionnaire;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionnaireActivity extends AppCompatActivity {

    private Client client;
    private Questionnaire questionnaire;
    private List<String> asks;
    private List<String> answers;
    private List<EditText> editTexts;
    private List<TextInputLayout> inputLayouts;
    private ProgressDialog progressDialog;
    private ClientAPI service;

    private int left, right;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        client = getIntent().getExtras().getParcelable(Client.class.getCanonicalName());
        initAsks();
        initUI();
    }

    private void checkFields(int FROM, int TO) {
        int count_et = 0;
        for(int i = FROM; i<=TO; i++){
            editTexts.get(count_et).setText(answers.get(i));
            inputLayouts.get(count_et).setHint(asks.get(i));
            ++count_et;
        }
        if(TO == 21){
            editTexts.get(2).setVisibility(View.INVISIBLE);
            editTexts.get(3).setVisibility(View.INVISIBLE);
            editTexts.get(4).setVisibility(View.INVISIBLE);
            inputLayouts.get(2).setVisibility(View.INVISIBLE);
            inputLayouts.get(3).setVisibility(View.INVISIBLE);
            inputLayouts.get(4).setVisibility(View.INVISIBLE);
        } else {
            editTexts.get(2).setVisibility(View.VISIBLE);
            editTexts.get(3).setVisibility(View.VISIBLE);
            editTexts.get(4).setVisibility(View.VISIBLE);
            inputLayouts.get(2).setVisibility(View.VISIBLE);
            inputLayouts.get(3).setVisibility(View.VISIBLE);
            inputLayouts.get(4).setVisibility(View.VISIBLE);
        }
    }

    private void initAsks() {
        asks = new ArrayList<>();
        answers = new ArrayList<>();
        asks.add(getResources().getString(R.string.what_is_your_name));
        asks.add(getResources().getString(R.string.do_you_like_your_name));
        asks.add(getResources().getString(R.string.your_old));
        asks.add(getResources().getString(R.string.old_feel));
        asks.add(getResources().getString(R.string.your_education));
        asks.add(getResources().getString(R.string.problem_with_health));
        asks.add(getResources().getString(R.string.think_about_yourself));
        asks.add(getResources().getString(R.string.people_think_about_you));
        asks.add(getResources().getString(R.string.why_you_visit_the_doctor));
        asks.add(getResources().getString(R.string.skills_how_result));
        asks.add(getResources().getString(R.string.your_family));
        asks.add(getResources().getString(R.string.revelations_with_friends));
        asks.add(getResources().getString(R.string.revelations_with_sex));
        asks.add(getResources().getString(R.string.do_you_have_family));
        asks.add(getResources().getString(R.string.your_hobby));
        asks.add(getResources().getString(R.string.gold_in_this_life));
        asks.add(getResources().getString(R.string.think_in_life));
        asks.add(getResources().getString(R.string.message_to_world));
        asks.add(getResources().getString(R.string.bad_do));
        asks.add(getResources().getString(R.string.bad_psychical));
        asks.add(getResources().getString(R.string.experience_with_doctor));
        asks.add(getResources().getString(R.string.main_info));
    }

    private void initUI() {
        progressDialog = new ProgressDialog(this);
        editTexts = new ArrayList<>();
        inputLayouts = new ArrayList<>();
        left = 0; right = 4;
        editTexts.add((EditText) findViewById(R.id.ask1));
        editTexts.add((EditText) findViewById(R.id.ask2));
        editTexts.add((EditText) findViewById(R.id.ask3));
        editTexts.add((EditText) findViewById(R.id.ask4));
        editTexts.add((EditText) findViewById(R.id.ask5));

        inputLayouts.add((TextInputLayout) findViewById(R.id.ask1_il));
        inputLayouts.add((TextInputLayout) findViewById(R.id.ask2_il));
        inputLayouts.add((TextInputLayout) findViewById(R.id.ask3_il));
        inputLayouts.add((TextInputLayout) findViewById(R.id.ask4_il));
        inputLayouts.add((TextInputLayout) findViewById(R.id.ask5_il));

        Button btn_back = (Button) findViewById(R.id.back);
        Button btn_forward = (Button) findViewById(R.id.forward);
        Button btn_send = (Button) findViewById(R.id.send);

        assert btn_forward != null;
        btn_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answers.size() == 0)
                    return;
                int count_et = 0;
                for(int i = left; i <= right; i++){
                    String ans = editTexts.get(count_et).getText().toString();
                    answers.set(i, ans);
                    count_et++;
                }
                left = right+1;
                int a = right+5;
                if (a <= 21) {
                    right += 5;
                    checkFields(left, right);
                }
                else {
                    right = 21;
                    checkFields(left, 21);
                }
            }
        });

        assert btn_back != null;
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answers.size() == 0)
                    return;
                int count_et = 0;
                for(int i = left; i <= right; i++){
                    String ans = editTexts.get(count_et).getText().toString();
                    answers.set(i, ans);
                    count_et++;
                }

                right = left-1;
                int a = left-5;
                if (a > 0) {
                    left -= 5;
                    checkFields(left, right);
                }
                else {
                    left = 0;
                    checkFields(0, right);
                }

            }
        });

        assert btn_send != null;
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count_et = 0;
                for(int i = left; i <= right; i++){
                    String ans = editTexts.get(count_et).getText().toString();
                    answers.set(i, ans);
                    count_et++;
                }
                Questionnaire questionnaire = new Questionnaire();
                questionnaire.setAsk1(answers.get(0));
                questionnaire.setAsk2(answers.get(1));
                questionnaire.setAsk3(Integer.parseInt(answers.get(2)));
                questionnaire.setAsk4(Integer.parseInt(answers.get(3)));
                questionnaire.setAsk5(answers.get(4));
                questionnaire.setAsk6(answers.get(5));
                questionnaire.setAsk71(answers.get(6));
                questionnaire.setAsk72(answers.get(7));
                questionnaire.setAsk8(answers.get(8));
                questionnaire.setAsk9(answers.get(9));
                questionnaire.setAsk10(answers.get(10));
                questionnaire.setAsk111(answers.get(11));
                questionnaire.setAsk112(answers.get(12));
                questionnaire.setAsk12(answers.get(13));
                questionnaire.setAsk13(answers.get(14));
                questionnaire.setAsk14(answers.get(15));
                questionnaire.setAsk15(answers.get(16));
                questionnaire.setAsk16(answers.get(17));
                questionnaire.setAsk17(answers.get(18));
                questionnaire.setAsk18(answers.get(19));
                questionnaire.setAsk19(answers.get(20));
                questionnaire.setAsk20(answers.get(21));
                questionnaire.setClientid(client.getLogin());
                Utils.initProgressDialog(progressDialog, getBaseContext());
                Call<Void> call = service.saveQuestionnaire(questionnaire);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()) {
                            Toast.makeText(getBaseContext(), R.string.questionnaire_refreshed, Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(getBaseContext(), R.string.questionnaire_error, Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getBaseContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });
            }
        });

        connectToServer();
    }

    private void connectToServer() {
        Utils.initProgressDialog(progressDialog, this);
        service = Utils.getRetrofit().create(ClientAPI.class);
        Call<Questionnaire> call = service.getQuestionnaireByLogin(client.getLogin());
        call.enqueue(new Callback<Questionnaire>() {
            @Override
            public void onResponse(Call<Questionnaire> call, Response<Questionnaire> response) {
                if (response.isSuccessful()) {
                    Log.e("samuliak", "Successful");
                    questionnaire = response.body();
                    initAnswers();
                }
                progressDialog.dismiss();
            }

            private void initAnswers() {
                answers.add(questionnaire.getAsk1());
                answers.add(questionnaire.getAsk2());
                answers.add(questionnaire.getAsk3().toString());
                answers.add(questionnaire.getAsk4().toString());
                answers.add(questionnaire.getAsk5());
                answers.add(questionnaire.getAsk6());
                answers.add(questionnaire.getAsk71());
                answers.add(questionnaire.getAsk72());
                answers.add(questionnaire.getAsk8());
                answers.add(questionnaire.getAsk9());
                answers.add(questionnaire.getAsk10());
                answers.add(questionnaire.getAsk111());
                answers.add(questionnaire.getAsk112());
                answers.add(questionnaire.getAsk12());
                answers.add(questionnaire.getAsk13());
                answers.add(questionnaire.getAsk14());
                answers.add(questionnaire.getAsk15());
                answers.add(questionnaire.getAsk16());
                answers.add(questionnaire.getAsk17());
                answers.add(questionnaire.getAsk18());
                answers.add(questionnaire.getAsk19());
                answers.add(questionnaire.getAsk20());
                checkFields(0, 4);
            }

            @Override
            public void onFailure(Call<Questionnaire> call, Throwable t) {
                if (!t.toString().contains("java.io.EOFException: End of input at line"))
                    Toast.makeText(getBaseContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }
}
