package com.example.vistomaisandroid.api;

import com.example.vistomaisandroid.dto.VeiculoDTO;

public class CadastroVeiculoAPI {

    // cadastrar veiculo no servidor
    public static void cadastrarVeiculo(VeiculoDTO veiculoDTOCadastrar, IOnCadastrarVeiculo iOnCadastrarVeiculo) {
        iOnCadastrarVeiculo.onRealizandoRequisicao();

        try {

        } catch (Exception e) {
            iOnCadastrarVeiculo.onErro("Erro: " + e.getMessage());
        }

    }

}
