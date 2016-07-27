package com.project.samuliak.psychogram.Util;


import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.widget.TextView;

import com.project.samuliak.psychogram.R;

public class Utils {
    //////// Очищение полей
    public static void clearFields(TextView loginTv, TextView passwordTv) {
        loginTv.setText("");
        passwordTv.setText("");
    }

    public static void clearDoctorFields(TextView tv1, TextView tv2, TextView tv3, TextView tv4,
                                         TextView tv5, TextView tv6, TextView tv7, TextView tv8,
                                         TextView tv9, TextView tv10, TextView tv11, TextView tv12,
                                         TextView tv13) {
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        tv4.setText("");
        tv5.setText("");
        tv6.setText("");
        tv7.setText("");
        tv8.setText("");
        tv9.setText("");
        tv10.setText("");
        tv11.setText("");
        tv12.setText("");
        tv13.setText("");
    }

    public static void clearClientFields(TextView tv1, TextView tv2, TextView tv3, TextView tv4,
                                         TextView tv5, TextView tv6, TextView tv7, TextView tv8) {
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        tv4.setText("");
        tv5.setText("");
        tv6.setText("");
        tv7.setText("");
        tv8.setText("");
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
}
