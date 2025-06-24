package com.example.vistomaisandroid.api;

import java.util.List;

public interface IOnConsultando<T> {

    void onRealizandoRequisicao();

    void onSucessoRequisicao(List<T> retornoListagem);

    void onErroRequisicao(String mensagemErro);

}
