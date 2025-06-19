package com.example.vistomaisandroid.api;

import com.example.vistomaisandroid.dto.UsuarioDTO;

public interface IOnLogin {

    void onSucessoLogin(UsuarioDTO usuarioLogadoDTO);

    void onErroLogin(String mensagemErro);

    void onProcessandoLogin();

}
