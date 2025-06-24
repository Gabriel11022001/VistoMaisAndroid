package com.example.vistomaisandroid.api;

import android.util.Log;

import java.util.ArrayList;

public class ListagemProprietariosAPI {

    // listar proprietarios do servidor
    public void listarProprietarios(IOnConsultando iOnConsultandoProprietariosServidor) {
        iOnConsultandoProprietariosServidor.onRealizandoRequisicao();

        try {
            iOnConsultandoProprietariosServidor.onSucessoRequisicao(new ArrayList());
        } catch (Exception e) {
            Log.e("erro_listar_proprietarios", "Erro ao tentar-se listar os proprietarios do servidor: " + e.getMessage());

            iOnConsultandoProprietariosServidor.onErroRequisicao("Erro ao tentar-se listar os propritários no servidor.");
        }

    }

}
