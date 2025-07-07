package com.example.vistomaisandroid.repositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "db_visto_mais";
    private static final int VERSAO = 1;
    private static SQLiteDatabase conexaoBancoDados = null;

    public DatabaseHelper(Context contextoApp) {
        super(contextoApp, NOME_BANCO, null, VERSAO);
    }

    // método invocado para criar a base de dados e as tabelas
    @Override
    public void onCreate(SQLiteDatabase db) {
        // criar tabela de proprietários
        db.execSQL("CREATE TABLE IF NOT EXISTS tb_proprietarios(" +
                "proprietario_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nome_completo TEXT NOT NULL," +
                "cpf TEXT NOT NULL," +
                "rg TEXT NOT NULL," +
                "telefone TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "data_nascimento TEXT NOT NULL," +
                "numero_cnh TEXT NOT NULL," +
                "proprietario_id_servidor INTEGER)");

        // criar tabela de endereços dos proprietários
        db.execSQL("CREATE TABLE tb_enderecos(" +
                "endereco_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "cep TEXT NOT NULL," +
                "complemento TEXT NOT NULL," +
                "logradouro TEXT NOT NULL," +
                "cidade TEXT NOT NULL," +
                "bairro TEXT NOT NULL," +
                "numero TEXT," +
                "estado TEXT NOT NULL," +
                "proprietario_id INTEGER NOT NULL," +
                "FOREIGN KEY(proprietario_id) REFERENCES tb_proprietarios(proprietario_id))");

        // criar tabela de categorias de veiculos
        db.execSQL("CREATE TABLE tb_categorias_veiculos(" +
                "categoria_veiculo_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nome_categoria TEXT)");

        // criar tabela de veiculos
        db.execSQL("CREATE TABLE tb_veiculos(" +
                "veiculo_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "modelo TEXT," +
                "marca TEXT," +
                "ano_lancamento INTEGER," +
                "ano_modelo INTEGER," +
                "cor TEXT," +
                "renavam TEXT," +
                "numero_chassi TEXT," +
                "categoria_veiculo_id INTEGER," +
                "proprietario_id INTEGER," +
                "FOREIGN KEY(categoria_veiculo_id) REFERENCES tb_categorias_veiculos(categoria_veiculo_id)," +
                "FOREIGN KEY(proprietarios_id) REFERENCES tb_proprietarios(proprietario_id))");
    }

    // método invocado para atualizar a estrutura do banco de dados
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // obter a instancia de conexão do banco de dados
    public SQLiteDatabase getConexaoBancoDados() {

        // implementando um singleton para a conexão com o banco de dados
        if (conexaoBancoDados == null) {
            conexaoBancoDados = this.getWritableDatabase();
        }

        return conexaoBancoDados;
    }

}
