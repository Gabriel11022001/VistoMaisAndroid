package com.example.vistomaisandroid.api;

import com.example.vistomaisandroid.dto.ProprietarioDTO;
import com.example.vistomaisandroid.model.Proprietario;

public class CadastroProprietarioAPI {

    // cadastrar proprietario no servidor
    public void cadastrarProprietario(ProprietarioDTO proprietarioDTOCadastrar, IOnCadastrar<Proprietario> iOnCadastrarProprietario) {
        iOnCadastrarProprietario.onRealizandoRequisicao();

        try {
            Proprietario proprietario = new Proprietario();

            proprietario.setNomeCompleto(proprietarioDTOCadastrar.getNomeCompleto());
            proprietario.setCpf(proprietarioDTOCadastrar.getCpf());
            proprietario.setRg(proprietarioDTOCadastrar.getRg());
            proprietario.setTelefone(proprietarioDTOCadastrar.getTelefone());
            proprietario.setEmail(proprietarioDTOCadastrar.getEmail());
            proprietario.setNumero(proprietarioDTOCadastrar.getNumeroCnh());
            proprietario.setDataNasciment(proprietarioDTOCadastrar.getDataNascimento());
            proprietario.setCep(proprietarioDTOCadastrar.getEnderecoProprietarioDTO().getCep());
            proprietario.setLogradouro(proprietarioDTOCadastrar.getEnderecoProprietarioDTO().getLogradouro());
            proprietario.setComplemento(proprietarioDTOCadastrar.getEnderecoProprietarioDTO().getComplemento());
            proprietario.setBairro(proprietarioDTOCadastrar.getEnderecoProprietarioDTO().getBairro());
            proprietario.setCidade(proprietarioDTOCadastrar.getEnderecoProprietarioDTO().getCidade());
            proprietario.setEstado(proprietarioDTOCadastrar.getEnderecoProprietarioDTO().getEstado());
            proprietario.setNumero(proprietarioDTOCadastrar.getEnderecoProprietarioDTO().getNumero());
        } catch (Exception e) {
            // erro no cadastro

            iOnCadastrarProprietario.onErroRequisicao("Erro ao tentar-se cadastrar o proprietário.");
        }

    }

}
