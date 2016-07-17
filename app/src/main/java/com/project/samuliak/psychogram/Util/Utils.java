package com.project.samuliak.psychogram.Util;


import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.widget.TextView;

public class Utils {
    //////// Очищение полей
    public static void clearFields(TextView loginTv, TextView passwordTv) {
        loginTv.setText("");
        passwordTv.setText("");
    }

    //////// Очищение подсказок
    public static void clearFieldHints(TextInputLayout log, TextInputLayout pas) {
        log.setError(null);
        pas.setError(null);
    }

    /////// Проверка на наявность интернета
    public static boolean isOnline(Context context) {
//        ConnectivityManager cm = (ConnectivityManager)
//                context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        return netInfo != null && netInfo.isConnectedOrConnecting();
        return true;
    }
}
