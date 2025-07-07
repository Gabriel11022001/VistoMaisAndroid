package com.example.vistomaisandroid.api;

import android.util.Log;

import com.example.vistomaisandroid.dto.ProprietarioDTO;

public class BuscarProprietarioPeloIdAPI {

    // buscar proprietário pelo id
    public void buscarProprietarioPeloId(int idProprietarioConsultar, IOnBuscarPeloId<ProprietarioDTO> iOnBuscarProprietarioPeloId) {
        iOnBuscarProprietarioPeloId.onRealizandoRequisicao();

        try {
            
        } catch (Exception e) {
            Log.e("erro_buscar_proprietario", "Erro ao tentar-se buscar o proprietário pelo id no servidor: " + e.getMessage());
        }

    }

}
