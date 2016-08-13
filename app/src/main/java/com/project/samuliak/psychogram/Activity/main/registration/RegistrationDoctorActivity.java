package com.project.samuliak.psychogram.Activity.main.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.project.samuliak.psychogram.Util.Constants;
import com.project.samuliak.psychogram.Util.Utils;
import com.transitionseverywhere.Recolor;
import com.transitionseverywhere.TransitionManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationDoctorActivity extends AppCompatActivity {

    private TextInputLayout loginRegIL, passwordRegIL, nameRegIL, surnameRegIL, direction_of_workRegIL;
    private TextView loginRegistr, passwordRegistr, nameRegistr, surnameRegistr, experienceRegistr,
            countryRegistr, cityRegistr, place_of_workRegistr, universityRegistr, specializationTV,
            direction_of_workRegistr;
    private Button btnReg, btn_man, btn_woman, btn_birthday;
    private String login, password, name, surname, birthday, country, city,
            place_of_work, university, prof_interest, specialization, competence, direction;
    private int experience;

    private ViewGroup container;

    private boolean isMan;

    private CircleImageView circleImageView;
    private Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_doctor);
        initUI();
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmptyMainComponents()) {
                    startRegistration();
                }
            }
        });
        setSex();
        setBirthday();
        linearClickListener();
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


    // лайнер выбора фотографии с галереи (!)
    private void linearClickListener() {
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("samuliak", "Click!");
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture")
                        , Constants.GALLERY_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("samuliak", "Activity result in Registr..");
        Bitmap img = null;
        if (requestCode == Constants.GALLERY_REQUEST) {
            if (data != null) {
                selectedImage = data.getData();
                try {
                    img = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                circleImageView.setImageBitmap(img);
                Log.e("samuliak", "path from act.result > " + getRealPathFromURI(selectedImage));
                String str = Utils.uploadFile(new File(getRealPathFromURI(selectedImage)));
                //Log.e("samuliak", "str.getValue > "+str.getValue());
            }
        }

    }

    private void initStrings() {
        login = loginRegistr.getText().toString();
        password = passwordRegistr.getText().toString();
        name = nameRegistr.getText().toString();
        surname = surnameRegistr.getText().toString();
        experience = Integer.parseInt(experienceRegistr.getText().toString());
        country = countryRegistr.getText().toString();
        city = cityRegistr.getText().toString();
        place_of_work = place_of_workRegistr.getText().toString();
        university = universityRegistr.getText().toString();
        direction = direction_of_workRegistr.getText().toString();
        specialization = specializationTV.getText().toString();
        initCompetence();
        initProfInterest();

    }

    private void initProfInterest() {
        CheckBox komp1 = (CheckBox) findViewById(R.id.inter1);
        CheckBox komp2 = (CheckBox) findViewById(R.id.inter2);
        CheckBox komp3 = (CheckBox) findViewById(R.id.inter3);
        CheckBox komp4 = (CheckBox) findViewById(R.id.inter4);
        CheckBox komp5 = (CheckBox) findViewById(R.id.inter5);
        CheckBox komp6 = (CheckBox) findViewById(R.id.inter6);
        CheckBox komp7 = (CheckBox) findViewById(R.id.inter7);
        CheckBox komp8 = (CheckBox) findViewById(R.id.inter8);
        prof_interest = "";

        if (komp1.isChecked())
            prof_interest += komp1.getText() + ":";
        if (komp2.isChecked())
            prof_interest += komp2.getText() + ":";
        if (komp3.isChecked())
            prof_interest += komp3.getText() + ":";
        if (komp4.isChecked())
            prof_interest += komp4.getText() + ":";
        if (komp5.isChecked())
            prof_interest += komp5.getText() + ":";
        if (komp6.isChecked())
            prof_interest += komp6.getText() + ":";
        if (komp7.isChecked())
            prof_interest += komp7.getText() + ":";
        if (komp8.isChecked())
            prof_interest += komp8.getText() + ":";

        Log.e("samuliak", "interes > "+prof_interest);
    }

    private void initCompetence() {
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
        competence = "";
        if (komp1.isChecked())
            competence += komp1.getText() + ":";
        if (komp2.isChecked())
            competence += komp2.getText() + ":";
        if (komp3.isChecked())
            competence += komp3.getText() + ":";
        if (komp4.isChecked())
            competence += komp4.getText() + ":";
        if (komp5.isChecked())
            competence += komp5.getText() + ":";
        if (komp6.isChecked())
            competence += komp6.getText() + ":";
        if (komp7.isChecked())
            competence += komp7.getText() + ":";
        if (komp8.isChecked())
            competence += komp8.getText() + ":";
        if (komp9.isChecked())
            competence += komp9.getText() + ":";
        if (komp10.isChecked())
            competence += komp10.getText() + ":";
        if (komp11.isChecked())
            competence += komp11.getText() + ":";
        if (komp12.isChecked())
            competence += komp12.getText() + ":";

        Log.e("samuliak", "competence > "+competence);
    }

    //   Инициализация UI компонентов
    private void initUI() {
        btnReg = (Button) findViewById(R.id.btnRegistr);
        btn_man = (Button) findViewById(R.id.btn_man);
        btn_woman = (Button) findViewById(R.id.btn_woman);
        btn_birthday = (Button) findViewById(R.id.btn_birthday);
        container = (ViewGroup) findViewById(R.id.container_btn);

        loginRegIL = (TextInputLayout) findViewById(R.id.loginRegIL);
        passwordRegIL = (TextInputLayout) findViewById(R.id.passwordRegIL);
        nameRegIL = (TextInputLayout) findViewById(R.id.nameRegIL);
        surnameRegIL = (TextInputLayout) findViewById(R.id.surnameRegIL);
        direction_of_workRegIL = (TextInputLayout) findViewById(R.id.direction_of_workRegIL);

        loginRegistr = (TextView) findViewById(R.id.loginRegistr);
        passwordRegistr = (TextView) findViewById(R.id.passwordRegistr);
        nameRegistr = (TextView) findViewById(R.id.nameRegistr);
        surnameRegistr = (TextView) findViewById(R.id.surnameRegistr);
        experienceRegistr = (TextView) findViewById(R.id.experienceRegistr);
        countryRegistr = (TextView) findViewById(R.id.countryRegistr);
        cityRegistr = (TextView) findViewById(R.id.cityRegistr);
        place_of_workRegistr = (TextView) findViewById(R.id.place_of_workRegistr);
        universityRegistr = (TextView) findViewById(R.id.universityRegistr);
        direction_of_workRegistr = (TextView) findViewById(R.id.direction_of_workRegistr);
        specializationTV = (TextView) findViewById(R.id.specializationRegistr);

        /*
        Добавить вк апи. Я пробывал, но уменя что то не выходит
         */


        circleImageView = (CircleImageView) findViewById(R.id.registr_image);
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
        PsychogolistAPI service = Utils.getRetrofit().create(PsychogolistAPI.class);
        ClientAPI proverkaService = Utils.getRetrofit().create(ClientAPI.class);
        String sex = "";
        if (isMan)
            sex = getResources().getString(R.string.man);
        else sex = getResources().getString(R.string.woman);
        final Psychologist doctor = new Psychologist(login, name, surname, password,
                experience, sex, birthday, country, city, prof_interest, place_of_work,
                university, specialization, competence, direction);
//        if(circleImageView.getBackground() != null)
//            user.setImage(Utils.drawableToBitmap(circleImageView.getBackground()));
//        else user.setImage(Utils
//                .drawableToBitmap(getResources().getDrawable(R.drawable.default_avatar)));
        final Call<Void> str = service.saveDoctor(doctor);
        Call<Client> proverka = proverkaService.getClientByLogin(login);
        proverka.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(getBaseContext(), R.string.unpossible_registred, Toast.LENGTH_LONG).show();
                    Log.e("samuliak", "not succesful  > " + response.message());
                    loginRegIL.setError(getResources().getString(R.string.login_is_busy));
                    YoYo.with(Techniques.Shake).duration(700).playOn(loginRegIL);
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Log.e("samuliak", "onFailure proverka succesful");
                str.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        progressDialog.dismiss();
                        if (response.isSuccessful()) {
                            Toast.makeText(getBaseContext(), R.string.registration_was_succesful,
                                    Toast.LENGTH_LONG).show();
                            clearUI();
                            Intent i = new Intent(getBaseContext(), AuthorizationActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putParcelable(Psychologist.class.getCanonicalName(), doctor);
                            i.putExtras(bundle);
                            i.putExtra("TYPE", "doctor");
                            startActivity(i);
                        } else {
                            Toast.makeText(getBaseContext(), R.string.unpossible_registred, Toast.LENGTH_LONG).show();
                            Log.e("samuliak", "not succesful  > " + response.message());
                            loginRegIL.setError(getResources().getString(R.string.login_is_busy));
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getBaseContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                        Log.e("samuliak", "onFailure > " + t.toString());
                        progressDialog.dismiss();
                        clearUI();
                    }
                });
            }
        });
    }

    private void clearUI() {
        Utils.clearDoctorFields(loginRegistr, nameRegistr, surnameRegistr, passwordRegistr,
                experienceRegistr, countryRegistr, cityRegistr, place_of_workRegistr,
                universityRegistr, direction_of_workRegistr);
        Utils.clearDoctorFieldHints(loginRegIL, passwordRegIL, nameRegIL, surnameRegIL, direction_of_workRegIL);
    }

    private String getRealPathFromURI(Uri contentURI) {
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    }
