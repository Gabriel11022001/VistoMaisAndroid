package com.example.vistomaisandroid.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ValidaEstaOnline {

    // validar se o usuário está com a internet conectada
    public static boolean isOnline(Context contextoApp) {
        ConnectivityManager connectivityManager = (ConnectivityManager) contextoApp.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {

            // o usuário está conectado
            return true;
        }

        // o usuário não está conectado
        return false;
    }

}
