package com.example.vistomaisandroid.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.vistomaisandroid.dto.EnderecoProprietarioDTO;
import com.example.vistomaisandroid.dto.ProprietarioDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProprietarioRepositorio extends RepositorioBase<ProprietarioDTO> {

    public ProprietarioRepositorio(Context contextoApp) throws Exception {
        // invocando o construtor da super classe(RepositorioBase) para fazer conexão com a base de dados
        super(contextoApp);
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

        int idProprietarioCadastrado = (int) this.bancoDados.insert("tb_proprietarios", null, contentValuesProprietarioCadastrar);

        // cadastrar endereço do proprietáiro
        ContentValues contentValuesEnderecoProprietario = new ContentValues();

        contentValuesEnderecoProprietario.put("cep", entidadeCadastrar.getEnderecoProprietarioDTO().getCep());
        contentValuesEnderecoProprietario.put("complemento", entidadeCadastrar.getEnderecoProprietarioDTO().getComplemento());
        contentValuesEnderecoProprietario.put("logradouro", entidadeCadastrar.getEnderecoProprietarioDTO().getLogradouro());
        contentValuesEnderecoProprietario.put("cidade", entidadeCadastrar.getEnderecoProprietarioDTO().getCidade());
        contentValuesEnderecoProprietario.put("bairro", entidadeCadastrar.getEnderecoProprietarioDTO().getBairro());
        contentValuesEnderecoProprietario.put("estado", entidadeCadastrar.getEnderecoProprietarioDTO().getEstado());
        contentValuesEnderecoProprietario.put("numero", entidadeCadastrar.getEnderecoProprietarioDTO().getNumero());
        contentValuesEnderecoProprietario.put("proprietario_id", idProprietarioCadastrado);

        this.bancoDados.insert("tb_enderecos", null, contentValuesEnderecoProprietario);

        Log.d("sucesso_cadastro_proprietario", "Proprietário cadastrado com sucesso na base de dados: " + idProprietarioCadastrado);
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
                "ON p.proprietario_id = e.proprietario_id " +
                "ORDER BY p.nome_completo ASC";

        Cursor cursor = this.bancoDados.rawQuery(queryConsultarProprietarios, null);

        List<ProprietarioDTO> proprietarios = new ArrayList<>();

        if (cursor != null) {

            while (cursor.moveToNext()) {
                ProprietarioDTO proprietarioDTO = new ProprietarioDTO();
                proprietarioDTO.setProprietarioId(cursor.getInt(cursor.getColumnIndex("proprietario_id")));
                proprietarioDTO.setNomeCompleto(cursor.getString(cursor.getColumnIndex("nome_completo")));
                proprietarioDTO.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
                proprietarioDTO.setRg(cursor.getString(cursor.getColumnIndex("rg")));
                proprietarioDTO.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
                proprietarioDTO.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                proprietarioDTO.setDataNascimento(cursor.getString(cursor.getColumnIndex("data_nascimento")));
                proprietarioDTO.setNumeroCnh(cursor.getString(cursor.getColumnIndex("numero_cnh")));

                EnderecoProprietarioDTO enderecoProprietarioDTO = new EnderecoProprietarioDTO();
                enderecoProprietarioDTO.setCep(cursor.getString(cursor.getColumnIndex("cep")));
                enderecoProprietarioDTO.setLogradouro(cursor.getString(cursor.getColumnIndex("logradouro")));
                enderecoProprietarioDTO.setComplemento(cursor.getString(cursor.getColumnIndex("complemento")));
                enderecoProprietarioDTO.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
                enderecoProprietarioDTO.setBairro(cursor.getString(cursor.getColumnIndex("bairro")));
                enderecoProprietarioDTO.setEstado(cursor.getString(cursor.getColumnIndex("estado")));
                enderecoProprietarioDTO.setNumero(cursor.getString(cursor.getColumnIndex("numero")));

                proprietarioDTO.setEnderecoProprietarioDTO(enderecoProprietarioDTO);

                proprietarios.add(proprietarioDTO);
            }

            cursor.close();
        }

        return Collections.unmodifiableList(proprietarios);
    }

    // buscar proprietário salvo na base local do app pelo id
    @Override
    public ProprietarioDTO buscarPeloId(Integer idEntidadeBuscar) {

        return null;
    }

}
