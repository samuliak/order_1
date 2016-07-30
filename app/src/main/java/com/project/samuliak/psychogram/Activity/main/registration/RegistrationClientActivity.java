package com.project.samuliak.psychogram.Activity.main.registration;

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
import com.project.samuliak.psychogram.API.ClientAPI;
import com.project.samuliak.psychogram.API.PsychogolistAPI;
import com.project.samuliak.psychogram.Activity.main.authornization.AuthorizationActivity;
import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.Model.Psychogolist;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Constants;
import com.project.samuliak.psychogram.Util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationClientActivity extends AppCompatActivity {
    private TextInputLayout loginRegIL, passwordRegIL, nameRegIL, surnameRegIL;
    private TextView loginRegistr, passwordRegistr, nameRegistr, surnameRegistr, ageRegistr,
            countryRegistr, cityRegistr, interestRegistr;
    private Button btnReg;
    private String login, password, name, surname, country, city, interest;
    private int age;

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
    }

    //   Инициализация UI компонентов
    private void initUI() {
        btnReg = (Button) findViewById(R.id.btnRegistr);
        loginRegIL = (TextInputLayout) findViewById(R.id.loginRegIL);
        passwordRegIL = (TextInputLayout) findViewById(R.id.passwordRegIL);
        nameRegIL = (TextInputLayout) findViewById(R.id.nameRegIL);
        surnameRegIL = (TextInputLayout) findViewById(R.id.surnameRegIL);

        loginRegistr = (TextView) findViewById(R.id.loginRegistr);
        passwordRegistr = (TextView) findViewById(R.id.passwordRegistr);
        nameRegistr = (TextView) findViewById(R.id.nameRegistr);
        surnameRegistr = (TextView) findViewById(R.id.surnameRegistr);
        ageRegistr = (TextView) findViewById(R.id.ageRegistr);
        countryRegistr = (TextView) findViewById(R.id.countryRegistr);
        cityRegistr = (TextView) findViewById(R.id.cityRegistr);
        interestRegistr = (TextView) findViewById(R.id.interestRegistr);
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

        final Client client = new Client(login, name, surname, password, age,
                country, city, interest);
//        if(circleImageView.getBackground() != null)
//            user.setImage(Utils.drawableToBitmap(circleImageView.getBackground()));
//        else user.setImage(Utils
//                .drawableToBitmap(getResources().getDrawable(R.drawable.default_avatar)));
        final Call<Psychogolist> proverka = proverkaService.getDoctorByName(client.getLogin());
        final Call<Void> str = service.saveClient(client);
        proverka.enqueue(new Callback<Psychogolist>() {
            @Override
            public void onResponse(Call<Psychogolist> call, Response<Psychogolist> response) {
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
            public void onFailure(Call<Psychogolist> call, Throwable t) {}

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
                ageRegistr, countryRegistr, cityRegistr, interestRegistr);
        Utils.clearClientFieldHints(loginRegIL, passwordRegIL, nameRegIL, surnameRegIL);
    }
}
