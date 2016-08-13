package com.project.samuliak.psychogram.Activity.main.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.project.samuliak.psychogram.API.ClientAPI;
import com.project.samuliak.psychogram.API.PsychogolistAPI;
import com.project.samuliak.psychogram.Activity.main.authornization.AuthorizationActivity;
import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.Model.Psychologist;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Utils;
import com.transitionseverywhere.Recolor;
import com.transitionseverywhere.TransitionManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationClientActivity extends AppCompatActivity {
    private TextInputLayout loginRegIL, passwordRegIL, nameRegIL, surnameRegIL;
    private TextView loginRegistr, passwordRegistr, nameRegistr, surnameRegistr,
            countryRegistr, cityRegistr;
    private Button btnReg, btn_man, btn_woman, btn_birthday;
    private String login, password, name, surname, birthday, country, city, interest;

    private ViewGroup container;

    private boolean isMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_client);
        initUI();
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegistration();
                if (!isEmptyMainComponents()){
                    startRegistration();
                }
            }
        });

        setSex();
        setBirthday();
    }

    private void setBirthday() {

        final SlideDateTimeListener listener = new SlideDateTimeListener() {

            @Override
            public void onDateTimeSet(Date date)
            {

                SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
                birthday = fmt.format(date);
                String str = getString(R.string.birthday) +": "+ birthday;
                btn_birthday.setText(str);

                TransitionManager.beginDelayedTransition(container, new Recolor());
                btn_birthday.setBackgroundDrawable(
                        new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
            }

            @Override
            public void onDateTimeCancel() {}
        };

        btn_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new SlideDateTimePicker.Builder(getSupportFragmentManager())
                        .setListener(listener)
                        .setTheme(SlideDateTimePicker.HOLO_LIGHT)
                        .setInitialDate(new Date())
                        .build()
                        .show();
            }
        });
    }

    // кнопки выбора пола
    private void setSex() {
        btn_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMan = true;
                setTransitional(btn_man, btn_woman);
            }

        });
        btn_woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMan = false;
                setTransitional(btn_woman, btn_man);
            }
        });

    }

    // меняет цвета кнопок определение пола
    private void setTransitional(Button btn_woman, Button btn_man) {
        TransitionManager.beginDelayedTransition(container, new Recolor());
        btn_woman.setBackgroundDrawable(
                new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        TransitionManager.beginDelayedTransition(container, new Recolor());
        btn_man.setBackgroundDrawable(
                new ColorDrawable(getResources().getColor(R.color.background_item_menu)));
    }

    private void initStrings() {
        login = loginRegistr.getText().toString();
        password = passwordRegistr.getText().toString();
        name = nameRegistr.getText().toString();
        surname = surnameRegistr.getText().toString();
        country = countryRegistr.getText().toString();
        city = cityRegistr.getText().toString();
        initInterest();
    }

    private void initInterest() {
        CheckBox komp1 = (CheckBox) findViewById(R.id.komp1);
        CheckBox komp2 = (CheckBox) findViewById(R.id.komp2);
        CheckBox komp3 = (CheckBox) findViewById(R.id.komp3);
        CheckBox komp4 = (CheckBox) findViewById(R.id.komp4);
        CheckBox komp5 = (CheckBox) findViewById(R.id.komp5);
        CheckBox komp6 = (CheckBox) findViewById(R.id.komp6);
        CheckBox komp7 = (CheckBox) findViewById(R.id.komp7);
        CheckBox komp8 = (CheckBox) findViewById(R.id.komp8);
        CheckBox komp9 = (CheckBox) findViewById(R.id.komp9);
        CheckBox komp10 = (CheckBox) findViewById(R.id.komp10);
        CheckBox komp11 = (CheckBox) findViewById(R.id.komp11);
        CheckBox komp12 = (CheckBox) findViewById(R.id.komp12);
        CheckBox komp13 = (CheckBox) findViewById(R.id.komp13);
        CheckBox komp14 = (CheckBox) findViewById(R.id.komp14);
        CheckBox komp15 = (CheckBox) findViewById(R.id.komp15);
        CheckBox komp16 = (CheckBox) findViewById(R.id.komp16);
        CheckBox komp17 = (CheckBox) findViewById(R.id.komp17);
        CheckBox komp18 = (CheckBox) findViewById(R.id.komp18);
        CheckBox komp19 = (CheckBox) findViewById(R.id.komp19);
        CheckBox komp20 = (CheckBox) findViewById(R.id.komp20);
        CheckBox komp21 = (CheckBox) findViewById(R.id.komp21);
        CheckBox komp22 = (CheckBox) findViewById(R.id.komp22);
        CheckBox komp23 = (CheckBox) findViewById(R.id.komp23);
        CheckBox komp24 = (CheckBox) findViewById(R.id.komp24);
        CheckBox komp25 = (CheckBox) findViewById(R.id.komp25);
        CheckBox komp26 = (CheckBox) findViewById(R.id.komp26);
        interest = "";
        if (komp1.isChecked())
            interest += komp1.getText() + ":";
        if (komp2.isChecked())
            interest += komp2.getText() + ":";
        if (komp3.isChecked())
            interest += komp3.getText() + ":";
        if (komp4.isChecked())
            interest += komp4.getText() + ":";
        if (komp5.isChecked())
            interest += komp5.getText() + ":";
        if (komp6.isChecked())
            interest += komp6.getText() + ":";
        if (komp7.isChecked())
            interest += komp7.getText() + ":";
        if (komp8.isChecked())
            interest += komp8.getText() + ":";
        if (komp9.isChecked())
            interest += komp9.getText() + ":";
        if (komp10.isChecked())
            interest += komp10.getText() + ":";
        if (komp11.isChecked())
            interest += komp11.getText() + ":";
        if (komp12.isChecked())
            interest += komp12.getText() + ":";
        if (komp13.isChecked())
            interest += komp12.getText() + ":";
        if (komp14.isChecked())
            interest += komp12.getText() + ":";
        if (komp15.isChecked())
            interest += komp12.getText() + ":";
        if (komp16.isChecked())
            interest += komp12.getText() + ":";
        if (komp17.isChecked())
            interest += komp12.getText() + ":";
        if (komp18.isChecked())
            interest += komp12.getText() + ":";
        if (komp19.isChecked())
            interest += komp12.getText() + ":";
        if (komp20.isChecked())
            interest += komp12.getText() + ":";
        if (komp21.isChecked())
            interest += komp12.getText() + ":";
        if (komp22.isChecked())
            interest += komp12.getText() + ":";
        if (komp23.isChecked())
            interest += komp12.getText() + ":";
        if (komp24.isChecked())
            interest += komp12.getText() + ":";
        if (komp25.isChecked())
            interest += komp12.getText() + ":";
        if (komp26.isChecked())
            interest += komp12.getText() + ":";

        Log.e("samuliak", "interest > "+interest);
    }

    //   Инициализация UI компонентов
    private void initUI() {
        btn_man = (Button) findViewById(R.id.btn_man);
        btn_woman = (Button) findViewById(R.id.btn_woman);
        btn_birthday = (Button) findViewById(R.id.btn_birthday);
        container = (ViewGroup) findViewById(R.id.container_btn);
        btnReg = (Button) findViewById(R.id.btnRegistr);
        loginRegIL = (TextInputLayout) findViewById(R.id.loginRegIL);
        passwordRegIL = (TextInputLayout) findViewById(R.id.passwordRegIL);
        nameRegIL = (TextInputLayout) findViewById(R.id.nameRegIL);
        surnameRegIL = (TextInputLayout) findViewById(R.id.surnameRegIL);

        loginRegistr = (TextView) findViewById(R.id.loginRegistr);
        passwordRegistr = (TextView) findViewById(R.id.passwordRegistr);
        nameRegistr = (TextView) findViewById(R.id.nameRegistr);
        surnameRegistr = (TextView) findViewById(R.id.surnameRegistr);
        countryRegistr = (TextView) findViewById(R.id.countryRegistr);
        cityRegistr = (TextView) findViewById(R.id.cityRegistr);
    }

    //   Проверям заполненные важные данные
    private boolean isEmptyMainComponents() {
        boolean ok = false;
        if(loginRegistr.getText().toString().isEmpty()) {
            loginRegIL.setError(getResources().getString(R.string.fill_the_field));
            YoYo.with(Techniques.Shake).duration(700).playOn(loginRegIL);
            ok = true;
        }
        if(passwordRegistr.getText().toString().isEmpty()) {
            passwordRegIL.setError(getResources().getString(R.string.fill_the_field));
            YoYo.with(Techniques.Shake).duration(700).playOn(passwordRegIL);
            ok = true;
        }
        if(nameRegistr.getText().toString().isEmpty()) {
            nameRegIL.setError(getResources().getString(R.string.fill_the_field));
            YoYo.with(Techniques.Shake).duration(700).playOn(nameRegIL);
            ok = true;
        }
        if(surnameRegistr.getText().toString().isEmpty()) {
            surnameRegIL.setError(getResources().getString(R.string.fill_the_field));
            YoYo.with(Techniques.Shake).duration(700).playOn(surnameRegIL);
            ok = true;
        }
        return ok;
    }

    //   Начало регистрации
    private void startRegistration() {
        initStrings();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.connecting_to_server));
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        /// Запрос на регистрацию
        registration(progressDialog);
    }

    private void registration(final ProgressDialog progressDialog) {
        PsychogolistAPI proverkaService = Utils.getRetrofit().create(PsychogolistAPI.class);
        ClientAPI service = Utils.getRetrofit().create(ClientAPI.class);

        String sex = "";
        if (isMan)
            sex = getResources().getString(R.string.man);
        else sex = getResources().getString(R.string.woman);

        final Client client = new Client(login, name, surname, password,
                birthday, sex, country, city, interest);
//        if(circleImageView.getBackground() != null)
//            user.setImage(Utils.drawableToBitmap(circleImageView.getBackground()));
//        else user.setImage(Utils
//                .drawableToBitmap(getResources().getDrawable(R.drawable.default_avatar)));
        final Call<Psychologist> proverka = proverkaService.getDoctorByName(client.getLogin());
        final Call<Void> str = service.saveClient(client);
        proverka.enqueue(new Callback<Psychologist>() {
            @Override
            public void onResponse(Call<Psychologist> call, Response<Psychologist> response) {
               if (response.isSuccessful())
                   loginIsBusy();
               else {
                    str.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            progressDialog.hide();
                            progressDialog.dismiss();
                            if (response.isSuccessful()) {
                                Toast.makeText(getBaseContext(), R.string.registration_was_succesful,
                                        Toast.LENGTH_LONG).show();
                                clearUI();
                                Intent i = new Intent(getBaseContext(), AuthorizationActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putParcelable(Client.class.getCanonicalName(), client);
                                i.putExtras(bundle);
                                i.putExtra("TYPE", "Client");
                                startActivity(i);
                            }
                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getBaseContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                            progressDialog.hide();
                            progressDialog.dismiss();
                            clearUI();
                        }
                    });
               }
            }

            @Override
            public void onFailure(Call<Psychologist> call, Throwable t) {}

            private void loginIsBusy() {
                progressDialog.hide();
                progressDialog.dismiss();
                Toast.makeText(getBaseContext(), R.string.unpossible_registred, Toast.LENGTH_LONG).show();
                loginRegIL.setError(getResources().getString(R.string.login_is_busy));
                YoYo.with(Techniques.Shake).duration(700).playOn(loginRegIL);
            }
        });
    }

    private void clearUI() {
        Utils.clearClientFields(loginRegistr, nameRegistr, surnameRegistr, passwordRegistr,
                countryRegistr, cityRegistr);
        Utils.clearClientFieldHints(loginRegIL, passwordRegIL, nameRegIL, surnameRegIL);
    }
}
