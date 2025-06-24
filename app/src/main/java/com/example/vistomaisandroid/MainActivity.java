package com.example.vistomaisandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vistomaisandroid.views.CadastroProprietarioActivity;
import com.example.vistomaisandroid.views.ProprietariosActivity;
import com.example.vistomaisandroid.views.VeiculosActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout containerGestaoVeiculos;
    private LinearLayout containerGestaoProprietarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mapear elementos de interface
        this.mapearElementosInterface();

        this.apresentarNomeUsuarioLogado();

        TextView txtTituloTelaHome = this.findViewById(R.id.txt_menu_topo_titulo);

        txtTituloTelaHome.setText("Home");
    }

    private void mapearElementosInterface() {
        this.containerGestaoVeiculos = this.findViewById(R.id.container_gestao_veiculos);
        this.containerGestaoProprietarios = this.findViewById(R.id.container_gestao_proprietarios);

        this.containerGestaoVeiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirecionar("gestao_veiculos");
            }
        });

        this.containerGestaoProprietarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirecionar("gestao_proprietarios");
            }
        });
    }

    private void apresentarNomeUsuarioLogado() {

    }

    private void redirecionar(String tela) {
        Intent intent = null;

        if (tela.equals("gestao_veiculos")) {
            // redirecionar o usuário para a tela com a gestão de veiculos
            intent = new Intent(this, VeiculosActivity.class);
        }

        if (tela.equals("gestao_proprietarios")) {
            // redirecionar o usuário para a tela com a gestão de proprietários
            intent = new Intent(this, ProprietariosActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
            finish();
        }

    }

}