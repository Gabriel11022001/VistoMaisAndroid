package com.example.vistomaisandroid.api;

public interface IOnCadastrar<T> {

    void onRealizandoRequisicao();

    void onSucessoRequisicao(T modelCadastrarServidor);

    void onErroRequisicao(String mensagemErro);

}
