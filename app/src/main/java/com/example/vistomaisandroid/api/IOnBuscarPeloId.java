package com.example.vistomaisandroid.api;

public interface IOnBuscarPeloId<T> {

    void onRealizandoRequisicao();

    void onSucessoRequisicao(T entidadeEncontrada);

    void onErroRequisicao(String mensagemErro);

}
