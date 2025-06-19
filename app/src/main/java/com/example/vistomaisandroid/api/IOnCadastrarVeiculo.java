package com.example.vistomaisandroid.api;

public interface IOnCadastrarVeiculo {

    void onRealizandoRequisicao();

    void onSucesso(String mensagemSucesso);

    void onErro(String mensagemErro);

}
