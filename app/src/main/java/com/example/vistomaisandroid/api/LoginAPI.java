package com.example.vistomaisandroid.api;

import android.util.Log;

import com.example.vistomaisandroid.dto.UsuarioDTO;
import com.example.vistomaisandroid.model.Usuario;

public class LoginAPI {

    // realizar login no servidor
    public void realizarLogin(UsuarioDTO usuarioLogarDTO, IOnLogin iOnLoginUsuario) {
        // processando o login
        iOnLoginUsuario.onProcessandoLogin();

        try {
            Usuario usuarioRealizarLogin = new Usuario();

            usuarioRealizarLogin.setEmail(usuarioLogarDTO.getEmail());
            usuarioRealizarLogin.setSenha(usuarioLogarDTO.getSenha());

        } catch (Exception e) {
            iOnLoginUsuario.onErroLogin("Erro ao tentar-se realizar login: " + e.getMessage());

            Log.d("erro_login_api", "Erro ao tentar-se realizar login: " + e.getMessage());
        }

    }

}
