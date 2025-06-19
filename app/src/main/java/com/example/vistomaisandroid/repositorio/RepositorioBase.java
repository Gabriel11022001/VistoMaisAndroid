package com.example.vistomaisandroid.repositorio;

import android.util.Log;

import java.util.List;

/**
 * classe que representa o repositório base, todos os
 * outros repositórios vão herdar dessa classe e é nela
 * que eu implemento a conexão com a base de dados local
 * do app
 */
public abstract class RepositorioBase<T> {

    public RepositorioBase() throws Exception {
        // implementar a conexão com a base local do app
        this.implementarConexaoBaseDados();
    }

    private void implementarConexaoBaseDados() throws Exception {

        try {

        } catch (Exception e) {
            Log.e("erro_conexao_base", "Erro ao tentar-se realizar a conexão com a base de dados: " + e.getMessage());

            throw e;
        }

    }

    public abstract void cadastrar(T entidadeCadastrar);

    public abstract void editar(T entidadeEditar);

    public abstract List<T> listar();

    public abstract T buscarPeloId(Integer idEntidadeBuscar);

}
