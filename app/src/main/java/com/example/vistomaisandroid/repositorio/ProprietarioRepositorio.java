package com.example.vistomaisandroid.repositorio;

import android.content.ContentValues;

import com.example.vistomaisandroid.dto.ProprietarioDTO;

import java.util.Collections;
import java.util.List;

public class ProprietarioRepositorio extends RepositorioBase<ProprietarioDTO> {

    public ProprietarioRepositorio() throws Exception {
        // invocando o construtor da super classe(RepositorioBase) para fazer conexão com a base de dados
        super();
    }

    // cadastrar proprietário na base local do app
    @Override
    public void cadastrar(ProprietarioDTO entidadeCadastrar) {
        ContentValues contentValuesProprietarioCadastrar = new ContentValues();

        // cadastrar proprietário
        contentValuesProprietarioCadastrar.put("nome_completo", entidadeCadastrar.getNomeCompleto());
        contentValuesProprietarioCadastrar.put("telefone", entidadeCadastrar.getTelefone());
        contentValuesProprietarioCadastrar.put("email", entidadeCadastrar.getEmail());
        contentValuesProprietarioCadastrar.put("cpf", entidadeCadastrar.getCpf());
        contentValuesProprietarioCadastrar.put("rg", entidadeCadastrar.getRg());
        contentValuesProprietarioCadastrar.put("data_nascimento", entidadeCadastrar.getDataNascimento());
        contentValuesProprietarioCadastrar.put("numero_cnh", entidadeCadastrar.getNumeroCnh());

        // cadastrar endereço do proprietáiro
        int isProprietarioCadastrado = 0;
        ContentValues contentValuesEnderecoProprietario = new ContentValues();

        contentValuesEnderecoProprietario.put("cep", entidadeCadastrar.getEnderecoProprietarioDTO().getCep());
        contentValuesEnderecoProprietario.put("complemento", entidadeCadastrar.getEnderecoProprietarioDTO().getComplemento());
        contentValuesEnderecoProprietario.put("logradouro", entidadeCadastrar.getEnderecoProprietarioDTO().getLogradouro());
        contentValuesEnderecoProprietario.put("cidade", entidadeCadastrar.getEnderecoProprietarioDTO().getCidade());
        contentValuesEnderecoProprietario.put("bairro", entidadeCadastrar.getEnderecoProprietarioDTO().getBairro());
        contentValuesEnderecoProprietario.put("estado", entidadeCadastrar.getEnderecoProprietarioDTO().getEstado());
        contentValuesEnderecoProprietario.put("numero", entidadeCadastrar.getEnderecoProprietarioDTO().getNumero());
    }

    // editar proprietário salvo na base local do app
    @Override
    public void editar(ProprietarioDTO entidadeEditar) {

    }

    // listar todos os proprietários salvos na base local do app
    @Override
    public List<ProprietarioDTO> listar() {
        String queryConsultarProprietarios = "SELECT p.proprietario_id, p.nome_completo, p.cpf," +
                "p.rg, p.data_nascimento, p.telefone, p.email, p.numero_cnh, e.cep, e.complemento," +
                "e.logradouro, e.cidade, e.bairro, e.numero, e.estado FROM tb_proprietarios AS p " +
                "INNER JOIN tb_enderecos AS e " +
                "OM p.proprietario_id = e.proprietario_id " +
                "ORDER BY p.nome_completo ASC";

        return Collections.emptyList();
    }

    // buscar proprietário salvo na base local do app pelo id
    @Override
    public ProprietarioDTO buscarPeloId(Integer idEntidadeBuscar) {

        return null;
    }

}
