package com.project.samuliak.psychogram.Activity.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.project.samuliak.psychogram.API.PsychogolistAPI;
import com.project.samuliak.psychogram.Activity.authornization.AuthorizationActivity;
import com.project.samuliak.psychogram.Model.Psychogolist;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Constants;
import com.project.samuliak.psychogram.Util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationDoctorActivity extends AppCompatActivity {

    private TextInputLayout loginRegIL, passwordRegIL, nameRegIL, surnameRegIL, direction_of_workRegIL;
    private TextView loginRegistr, passwordRegistr, nameRegistr, surnameRegistr, ageRegistr,
            countryRegistr, cityRegistr, interestRegistr, place_of_workRegistr, universityRegistr,
            specializationRegistr, competenceRegistr, direction_of_workRegistr;
    private Button btnReg;
    private String login, password, name, surname, country, city, interest, place_of_work,
                university, specialization, competence, direction;
    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_doctor);
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
    }

    private void initStrings() {
        login = loginRegistr.getText().toString();
        password = passwordRegistr.getText().toString();
        name = nameRegistr.getText().toString();
        surname = surnameRegistr.getText().toString();
        age = Integer.parseInt(ageRegistr.getText().toString());
        country = countryRegistr.getText().toString();
        city = cityRegistr.getText().toString();
        interest = interestRegistr.getText().toString();
        place_of_work = place_of_workRegistr.getText().toString();
        university = universityRegistr.getText().toString();
        specialization = specializationRegistr.getText().toString();
        competence = competenceRegistr.getText().toString();
        direction = direction_of_workRegistr.getText().toString();
    }

    //   Инициализация UI компонентов
    private void initUI() {
        btnReg = (Button) findViewById(R.id.btnRegistr);
        loginRegIL = (TextInputLayout) findViewById(R.id.loginRegIL);
        passwordRegIL = (TextInputLayout) findViewById(R.id.passwordRegIL);
        nameRegIL = (TextInputLayout) findViewById(R.id.nameRegIL);
        surnameRegIL = (TextInputLayout) findViewById(R.id.surnameRegIL);
        direction_of_workRegIL = (TextInputLayout) findViewById(R.id.direction_of_workRegIL);

        loginRegistr = (TextView) findViewById(R.id.loginRegistr);
        passwordRegistr = (TextView) findViewById(R.id.passwordRegistr);
        nameRegistr = (TextView) findViewById(R.id.nameRegistr);
        surnameRegistr = (TextView) findViewById(R.id.surnameRegistr);
        ageRegistr = (TextView) findViewById(R.id.ageRegistr);
        countryRegistr = (TextView) findViewById(R.id.countryRegistr);
        cityRegistr = (TextView) findViewById(R.id.cityRegistr);
        interestRegistr = (TextView) findViewById(R.id.interestRegistr);
        place_of_workRegistr = (TextView) findViewById(R.id.place_of_workRegistr);
        universityRegistr = (TextView) findViewById(R.id.universityRegistr);
        specializationRegistr = (TextView) findViewById(R.id.specializationRegistr);
        competenceRegistr = (TextView) findViewById(R.id.competenceRegistr);
        direction_of_workRegistr = (TextView) findViewById(R.id.direction_of_workRegistr);
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
        if(direction_of_workRegistr.getText().toString().isEmpty()) {
            direction_of_workRegIL.setError(getResources().getString(R.string.fill_the_field));
            YoYo.with(Techniques.Shake).duration(700).playOn(direction_of_workRegIL);
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
        Retrofit client = new Retrofit.Builder()
                .baseUrl(Constants.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PsychogolistAPI service = client.create(PsychogolistAPI.class);

        final Psychogolist doctor = new Psychogolist(login, name, surname, password, age,
                country, city, interest, place_of_work, university, specialization,
                competence, direction);
//        if(circleImageView.getBackground() != null)
//            user.setImage(Utils.drawableToBitmap(circleImageView.getBackground()));
//        else user.setImage(Utils
//                .drawableToBitmap(getResources().getDrawable(R.drawable.default_avatar)));
        final Call<Void> str = service.createDoctor(doctor);
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
                    bundle.putParcelable(Psychogolist.class.getCanonicalName(), doctor);
                    i.putExtras(bundle);
                    startActivity(i);
                } else {
                    Toast.makeText(getBaseContext(), R.string.unpossible_registred, Toast.LENGTH_LONG).show();
                    loginRegIL.setError("Логин занят! ");
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

    private void clearUI() {
        Utils.clearDoctorFields(loginRegistr, nameRegistr, surnameRegistr, passwordRegistr,
                ageRegistr, countryRegistr, cityRegistr, interestRegistr, place_of_workRegistr,
                universityRegistr, specializationRegistr, competenceRegistr, direction_of_workRegistr);
        Utils.clearDoctorFieldHints(loginRegIL, passwordRegIL, nameRegIL, surnameRegIL, direction_of_workRegIL);
    }
}
