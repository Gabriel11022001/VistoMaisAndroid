package com.example.vistomaisandroid.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.vistomaisandroid.R;

public class Loader {

    private static AlertDialog loader;

    public static void apresentar(String mensagem, Context contextoApp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(contextoApp);

        builder.setCancelable(false);

        View viewLoader = LayoutInflater.from(contextoApp).inflate(R.layout.loader, null);

        TextView txtMensagemLoader = viewLoader.findViewById(R.id.txt_mensagem_loader);

        txtMensagemLoader.setText(mensagem.trim());

        builder.setView(viewLoader);

        loader = builder.create();

        loader.show();
    }

    public static void fechar() {
        loader.dismiss();
    }

}
