package com.example.vistomaisandroid.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vistomaisandroid.MainActivity;
import com.example.vistomaisandroid.R;
import com.example.vistomaisandroid.api.IOnLogin;
import com.example.vistomaisandroid.api.LoginAPI;
import com.example.vistomaisandroid.dto.UsuarioDTO;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmailUsuario;
    private EditText edtSenhaUsuario;
    private Button btnLogin;
    private LinearLayout btnLoginCarregando;
    private LoginAPI loginApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.edtEmailUsuario = this.findViewById(R.id.edt_email_usuario);
        this.edtSenhaUsuario = this.findViewById(R.id.edt_senha_usuario);
        this.btnLogin = this.findViewById(R.id.btn_login);
        this.btnLoginCarregando = this.findViewById(R.id.btn_carregando_login);

        this.btnLogin.setVisibility(View.VISIBLE);
        this.btnLoginCarregando.setVisibility(View.GONE);

        // realizar login
        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        // esconder a ActionBar na tela de login
        this.esconderActionBar();

        this.loginApi = new LoginAPI();
    }

    private boolean validarEmailSenha() {
        Boolean ok = true;

        String email = this.edtEmailUsuario.getText().toString();
        String senha = this.edtSenhaUsuario.getText().toString();

        if (email.isBlank()) {
            this.apresentarAlertaErro("Informe o e-mail.");
            ok = false;
        } else if (senha.isBlank()) {
            this.apresentarAlertaErro("Informe a senha.");
            ok = false;
        }

        return ok;
    }

    // apresentar alerta de erro para o usuário
    private void apresentarAlertaErro(String mensagemErro) {
        Toast.makeText(this, mensagemErro, Toast.LENGTH_LONG).show();
    }

    // listener para controlar o login do usuário
    private IOnLogin controlarLoginServidorListener() {

        return new IOnLogin() {
            @Override
            public void onSucessoLogin(UsuarioDTO usuarioLogadoDTO) {
                // realizou login com sucesso

            }

            @Override
            public void onErroLogin(String mensagemErro) {
                // erro ao tentar-se realizar login

                apresentarAlertaErro(mensagemErro);

                btnLogin.setVisibility(View.VISIBLE);
                btnLoginCarregando.setVisibility(View.GONE);
                edtEmailUsuario.setEnabled(true);
                edtSenhaUsuario.setEnabled(true);
            }

            @Override
            public void onProcessandoLogin() {
                // processando a requisição
            }
        };
    }

    // realizar login
    private void login() {

        try {
            this.btnLogin.setVisibility(View.GONE);
            this.btnLoginCarregando.setVisibility(View.VISIBLE);

            this.edtEmailUsuario.setEnabled(false);
            this.edtSenhaUsuario.setEnabled(false);

            if (this.validarEmailSenha()) {
                // e-mail e senha corretos
                String email = this.edtEmailUsuario.getText().toString().trim();
                String senha = this.edtSenhaUsuario.getText().toString().trim();

                UsuarioDTO usuarioDTO = new UsuarioDTO();

                usuarioDTO.setEmail(email);
                usuarioDTO.setSenha(senha);

                // this.loginApi.realizarLogin(usuarioDTO, this.controlarLoginServidorListener());
                Intent intentTelaHome = new Intent(this, MainActivity.class);
                startActivity(intentTelaHome);
                finish();
            } else {
                this.btnLogin.setVisibility(View.VISIBLE);
                this.btnLoginCarregando.setVisibility(View.GONE);

                this.edtEmailUsuario.setEnabled(true);
                this.edtSenhaUsuario.setEnabled(true);
            }

        } catch (Exception e) {
            this.apresentarAlertaErro("Erro: " + e.getMessage());

            this.btnLogin.setVisibility(View.VISIBLE);
            this.btnLoginCarregando.setVisibility(View.GONE);

            this.edtEmailUsuario.setEnabled(true);
            this.edtSenhaUsuario.setEnabled(true);

            Log.d("erro_login", "Erro ao tentar-se realizar login: " + e.getMessage());
        }

    }

    private void esconderActionBar() {

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

    }

}