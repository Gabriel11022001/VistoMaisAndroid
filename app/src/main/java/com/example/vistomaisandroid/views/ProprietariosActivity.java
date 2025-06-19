package com.example.vistomaisandroid.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vistomaisandroid.MainActivity;
import com.example.vistomaisandroid.R;
import com.example.vistomaisandroid.dto.ProprietarioDTO;
import com.example.vistomaisandroid.repositorio.ProprietarioRepositorio;

import java.util.ArrayList;
import java.util.List;

public class ProprietariosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewListagemProprietarios;
    private ProprietarioRepositorio proprietarioRepositorio;
    private List<ProprietarioDTO> proprietarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_proprietarios);

        // mapear elementos de interface
        this.recyclerViewListagemProprietarios = this.findViewById(R.id.recycler_listagem_proprietarios);

        // configurar a recyclerview com listagem dos proprietários
        this.configurarRecyclerView();

        TextView txtTituloListagemProprietarios = this.findViewById(R.id.txt_menu_topo_titulo);
        ImageButton btnRetornar = this.findViewById(R.id.btn_back_menu_topo);

        txtTituloListagemProprietarios.setText("Proprietários");

        // mapear evento de clique no botão de retornar
        btnRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        try {
            this.proprietarioRepositorio = new ProprietarioRepositorio();
        } catch (Exception e) {
            // apresentar alerta de erro para o usuário informando que houve erro na hora de conectar na base de dados
        }

    }

    private void configurarRecyclerView() {

    }

    @Override
    protected void onStart() {
        super.onStart();

        // buscar proprietários na base de dados
        this.proprietarios = this.proprietarioRepositorio.listar();

        if (this.proprietarios.size() > 0) {
            // popular recyclerview
        } else {
            // apresentar mensagem informando que não existem proprietários cadastrados
        }

    }

    private void retornar() {
        // retornar para a tela home
        Intent intentTelaHome = new Intent(this, MainActivity.class);
        startActivity(intentTelaHome);
        finish();
    }

    // controlar evento de retornar para a tela home
    @Override
    public void onBackPressed() {
        this.retornar();
    }

}