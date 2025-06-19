package com.example.vistomaisandroid.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vistomaisandroid.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().hide();
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