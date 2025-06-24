package com.example.vistomaisandroid.repositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

/**
 * classe que representa o repositório base, todos os
 * outros repositórios vão herdar dessa classe e é nela
 * que eu implemento a conexão com a base de dados local
 * do app
 */
public abstract class RepositorioBase<T> {

    protected SQLiteDatabase bancoDados = null;
    private Context contextoApp;

    public RepositorioBase(Context contextoApp) throws Exception {
        this.contextoApp = contextoApp;
        // implementar a conexão com a base local do app
        this.implementarConexaoBaseDados();
    }

    private void implementarConexaoBaseDados() throws Exception {

        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(this.contextoApp);

            this.bancoDados = databaseHelper.getConexaoBancoDados();
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
