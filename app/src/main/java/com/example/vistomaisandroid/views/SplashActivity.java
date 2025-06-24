package com.example.vistomaisandroid.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vistomaisandroid.R;
import com.example.vistomaisandroid.repositorio.DatabaseHelper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
        }

        // criar base de dados e as tabelas
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
        } catch (Exception e) {
            Log.e("erro_criar_base", "Erro ao tentar-se criar o banco de dados: " + e.getMessage());
        }

        this.redirecionarUsuarioTelaLogin();
    }

    // após 3 segundos, redirecionar o usuário para a tela de login
    private void redirecionarUsuarioTelaLogin() {
        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);

                        startActivity(intentLogin);
                        finish();

                    }
                }, 3000);
    }

}