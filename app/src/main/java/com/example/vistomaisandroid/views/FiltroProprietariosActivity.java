package com.example.vistomaisandroid.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vistomaisandroid.R;
import com.example.vistomaisandroid.repositorio.ProprietarioRepositorio;
import com.example.vistomaisandroid.utils.FiltroProprietarios;

public class FiltroProprietariosActivity extends AppCompatActivity implements View.OnClickListener {

    private ProprietarioRepositorio proprietarioRepositorio;
    private FiltroProprietarios filtroProprietarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_filtro_proprietarios);

        TextView txtTituloTelaFiltroProprietarios = this.findViewById(R.id.txt_menu_topo_titulo);
        ImageButton btnRetornar = this.findViewById(R.id.btn_back_menu_topo);
        Button btnAplicarFiltro = this.findViewById(R.id.btn_aplicar_filtro_proprietarios);
        Button btnLimparFiltro = this.findViewById(R.id.btn_limpar_filtro_proprietarios);

        txtTituloTelaFiltroProprietarios.setText("Filtro de proprietários");

        btnRetornar.setOnClickListener(this);
        btnAplicarFiltro.setOnClickListener(this);
        btnLimparFiltro.setOnClickListener(this);

        try {
            this.proprietarioRepositorio = new ProprietarioRepositorio(this);
        } catch (Exception e) {

        }

        this.filtroProprietarios = new FiltroProprietarios(this.proprietarioRepositorio);
    }

    private void retornar() {

    }

    @Override
    public void onBackPressed() {
        this.retornar();
    }

    // aplicar o filtro de proprietários
    private void aplicarFiltro() {

        try {
            Intent intent = new Intent(this, ProprietariosActivity.class);

            intent.putExtra("filtro_proprietarios", this.filtroProprietarios);

            startActivity(intent);
            finish();
        } catch (Exception e) {
            Log.e("erro_filtro_proprietarios", e.getMessage());
        }

    }

    // limpar o filtro de proprietários
    private void limparFiltro() {

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_back_menu_topo) {
            // clicou no botão de retornar
            this.onBackPressed();
        }

        if (v.getId() == R.id.btn_aplicar_filtro_proprietarios) {
            // o usuário clicou no botão para aplicar o filtro
            this.aplicarFiltro();
        }

        if (v.getId() == R.id.btn_limpar_filtro_proprietarios) {
            // o usuário clicou no botão para limpar o filtro
            this.limparFiltro();
        }

    }

}