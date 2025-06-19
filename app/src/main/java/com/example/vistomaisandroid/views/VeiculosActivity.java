package com.example.vistomaisandroid.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vistomaisandroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VeiculosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiculos);

        FloatingActionButton btnCadastrarNovoVeiculo = this.findViewById(R.id.btn_cadastrar_novo_veiculo);

        btnCadastrarNovoVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirecionar o usuário para a tela de cadastro de um novo veiculo
                cadastrarNovoVeiculo();
            }
        });
    }

    private void cadastrarNovoVeiculo() {
        Intent intentTelaCadastroNovoVeiculo = new Intent(this, CadastroVeiculoActivity.class);

        startActivity(intentTelaCadastroNovoVeiculo);
        finish();
    }

}