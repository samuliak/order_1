package com.project.samuliak.psychogram.Util;


import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.project.samuliak.psychogram.API.PsychogolistAPI;
import com.project.samuliak.psychogram.R;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {
    //////// Очищение полей
    public static void clearFields(TextView loginTv, TextView passwordTv) {
        loginTv.setText("");
        passwordTv.setText("");
    }

    public static void clearDoctorFields(TextView tv1, TextView tv2, TextView tv3, TextView tv4,
                                         TextView tv5, TextView tv8, TextView tv9, TextView tv10,
                                         TextView tv11, TextView tv13) {
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        tv4.setText("");
        tv5.setText("");
        tv8.setText("");
        tv9.setText("");
        tv10.setText("");
        tv11.setText("");
        tv13.setText("");
    }

    public static void clearClientFields(TextView tv1, TextView tv2, TextView tv3, TextView tv4,
                                         TextView tv5, TextView tv7) {
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        tv4.setText("");
        tv5.setText("");
        tv7.setText("");
    }

    //////// Очищение подсказок
    public static void clearFieldHints(TextInputLayout log, TextInputLayout pas) {
        log.setError(null);
        pas.setError(null);
    }

    public static void clearDoctorFieldHints(TextInputLayout tv1, TextInputLayout tv2,
                                             TextInputLayout tv3, TextInputLayout tv4,
                                             TextInputLayout tv5) {
        tv1.setError(null);
        tv2.setError(null);
        tv3.setError(null);
        tv4.setError(null);
        tv5.setError(null);
    }

    public static void clearClientFieldHints(TextInputLayout tv1, TextInputLayout tv2,
                                             TextInputLayout tv3, TextInputLayout tv4) {
        tv1.setError(null);
        tv2.setError(null);
        tv3.setError(null);
        tv4.setError(null);
    }

    /////// Проверка на наявность интернета
    public static boolean isOnline(Context context) {
//        ConnectivityManager cm = (ConnectivityManager)
//                context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        return netInfo != null && netInfo.isConnectedOrConnecting();
        return true;
    }


    public static void initProgressDialog(ProgressDialog progressDialog, Context context) {
        progressDialog.setMessage(context.getResources().getString(R.string.connecting_to_server));
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    // Одержать ретрофит
    public static Retrofit getRetrofit(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateTypeAdapter());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.HOST)
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                .build();
        return retrofit;
    }


    public static String uploadFile(File file) {
        PsychogolistAPI service = getRetrofit().create(PsychogolistAPI.class);

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

        String descriptionString = "image desctription";

        RequestBody description =
                RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
        Call<String> call = service.uploadFile(description, body);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call,
                                   Response<String> response) {
                if (response.isSuccessful()) {
                    Log.e("samuliak", "Succesful:" + response.body());
                } else
                    Log.e("samuliak", "not succesful > "+response.message());
                Log.e("samuliak", "body > :" + response.body());
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("samuliak", "Upload error:"+t.toString());
            }
        });
        Log.e("samuliak", "uploadFile. End.");
        return "Dsd";
    }


    private static class DateTypeAdapter implements JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return new Date(json.getAsLong());
        }
    }
}
