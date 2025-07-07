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

        if (entidadeCadastrar.getProprietarioIdServidor() != 0) {
            contentValuesProprietarioCadastrar.put("proprietario_id_servidor", entidadeCadastrar.getProprietarioIdServidor());
        } else {
            contentValuesProprietarioCadastrar.put("proprietario_id_servidor", 0);
        }

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
                "p.rg, p.data_nascimento, p.telefone, p.email, p.numero_cnh, p.proprietario_id_servidor, e.cep, e.complemento," +
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
        ProprietarioDTO proprietarioDTO = null;

        Cursor cursor = this.bancoDados.rawQuery("SELECT p.*, e.cep, e.complemento," +
                "e.logradouro, e.cep, e.endereco_id, e.cidade, e.bairro, e.estado, e.numero " +
                "FROM tb_proprietarios AS p INNER JOIN tb_enderecos AS e " +
                "ON p.proprietario_id = e.proprietario_id " +
                "AND p.proprietario_id = ?", new String[]{ String.valueOf(idEntidadeBuscar) });

        if (cursor != null) {

            if (cursor.moveToFirst()) {
                proprietarioDTO = new ProprietarioDTO();

                proprietarioDTO.setProprietarioId(cursor.getInt(cursor.getColumnIndex("proprietario_id")));
                proprietarioDTO.setProprietarioIdServidor(cursor.getInt(cursor.getColumnIndex("proprietario_id_servidor")));
                proprietarioDTO.setNomeCompleto(cursor.getString(cursor.getColumnIndex("nome_completo")));
                proprietarioDTO.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
                proprietarioDTO.setRg(cursor.getString(cursor.getColumnIndex("rg")));
                proprietarioDTO.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
                proprietarioDTO.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                proprietarioDTO.setDataNascimento(cursor.getString(cursor.getColumnIndex("data_nascimento")));
                proprietarioDTO.setNumeroCnh(cursor.getString(cursor.getColumnIndex("numero_cnh")));

                EnderecoProprietarioDTO enderecoProprietarioDTO = new EnderecoProprietarioDTO();
                enderecoProprietarioDTO.setEnderecoId(cursor.getInt(cursor.getColumnIndex("endereco_id")));
                enderecoProprietarioDTO.setCep(cursor.getString(cursor.getColumnIndex("cep")));
                enderecoProprietarioDTO.setLogradouro(cursor.getString(cursor.getColumnIndex("logradouro")));
                enderecoProprietarioDTO.setComplemento(cursor.getString(cursor.getColumnIndex("complemento")));
                enderecoProprietarioDTO.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
                enderecoProprietarioDTO.setBairro(cursor.getString(cursor.getColumnIndex("bairro")));
                enderecoProprietarioDTO.setEstado(cursor.getString(cursor.getColumnIndex("estado")));
                enderecoProprietarioDTO.setNumero(cursor.getString(cursor.getColumnIndex("numero")));

                proprietarioDTO.setEnderecoProprietarioDTO(enderecoProprietarioDTO);
            }

            cursor.close();
        }

        return proprietarioDTO;
    }

    // buscar id do proprietário no servidor
    public int buscarIdProprietarioServidor(int idProprietarioBaseLocal) {
        int idProprietarioServidor = 0;


        Cursor cursor = this.bancoDados.rawQuery("SELECT proprietario_id_servidor FROM tb_proprietarios WHERE proprietario_id = ?", new String[]{
                String.valueOf(idProprietarioBaseLocal)
        });

        if (cursor != null) {

            if (cursor.moveToFirst()) {
                idProprietarioServidor = cursor.getInt(cursor.getColumnIndex("proprietario_id_servidor"));
            }

            cursor.close();
        }

        return idProprietarioBaseLocal;
    }

    // editar proprietário que está vindo do servidor
    public void editarProprietarioVindoServidor(String cpf, ProprietarioDTO proprietarioDTOEditar) {
        Cursor cursor = this.bancoDados.rawQuery("SELECT * FROM tb_proprietarios WHERE cpf = :cpf", new String[]{
                cpf
        });

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome_completo", proprietarioDTOEditar.getNomeCompleto());
        contentValues.put("cpf", proprietarioDTOEditar.getCpf());
        contentValues.put("rg", proprietarioDTOEditar.getRg());
        contentValues.put("telefone", proprietarioDTOEditar.getTelefone());
        contentValues.put("email", proprietarioDTOEditar.getEmail());
        contentValues.put("data_nascimento", proprietarioDTOEditar.getDataNascimento());
        contentValues.put("numero_cnh", proprietarioDTOEditar.getNumeroCnh());
        contentValues.put("proprietario_id_servidor", proprietarioDTOEditar.getProprietarioIdServidor());

        ContentValues contentValuesEndereco = new ContentValues();
        contentValuesEndereco.put("cep", proprietarioDTOEditar.getEnderecoProprietarioDTO().getCep());
        contentValuesEndereco.put("complemento", proprietarioDTOEditar.getEnderecoProprietarioDTO().getComplemento());
        contentValuesEndereco.put("logradouro", proprietarioDTOEditar.getEnderecoProprietarioDTO().getLogradouro());
        contentValuesEndereco.put("cidade", proprietarioDTOEditar.getEnderecoProprietarioDTO().getCidade());
        contentValuesEndereco.put("bairro", proprietarioDTOEditar.getEnderecoProprietarioDTO().getBairro());
        contentValuesEndereco.put("estado", proprietarioDTOEditar.getEnderecoProprietarioDTO().getEstado());
        contentValuesEndereco.put("numero", proprietarioDTOEditar.getEnderecoProprietarioDTO().getNumero());

        if (cursor != null) {

            if (cursor.moveToFirst()) {
                // editar o proproprietario com o cpf informado
                int idProprietarioEditar = cursor.getInt(cursor.getColumnIndex("proprietario_id"));

                this.bancoDados.update("tb_proprietarios", contentValues, "proprietario_id = ?", new String[]{ String.valueOf(idProprietarioEditar) });

                // editar o endereço
                this.bancoDados.update("tb_enderecos", contentValuesEndereco, "proprietario_id = ?", new String[]{ String.valueOf(idProprietarioEditar) });
            } else {
                // cadastrar um novo proprietário pois o mesmo não  está na base local do app
                int idProprietarioCadastrado = (int) this.bancoDados.insert("tb_proprietarios", null, contentValues);

                contentValuesEndereco.put("proprietario_id", idProprietarioCadastrado);

                this.bancoDados.insert("tb_enderecos", null, contentValuesEndereco);
            }

            cursor.close();
        } else {
            // cadastrar um novo proprietário pois o mesmo não  está na base local do app
            int idProprietarioCadastrado = (int) this.bancoDados.insert("tb_proprietarios", null, contentValues);

            contentValuesEndereco.put("proprietario_id", idProprietarioCadastrado);

            this.bancoDados.insert("tb_enderecos", null, contentValuesEndereco);
        }

    }

    // buscar proprietário pelo id
    public ProprietarioDTO buscarProprietarioPeloCpf(String cpf) {
        ProprietarioDTO proprietarioDTO = null;

        String query = "SELECT p.*, e.cep, e.complemento, e.logradouro, e.endereco_id," +
                "e.cidade, e.bairro, e.estado, e.numero " +
                "FROM tb_proprietarios AS p INNER JOIN tb_enderecos AS e " +
                "ON p.proprietario_id = e.proprietario_id " +
                "AND p.cpf = ?";

        Cursor cursor = this.bancoDados.rawQuery(query, new String[]{ cpf });

        if (cursor != null) {

            if (cursor.moveToFirst()) {
                proprietarioDTO = new ProprietarioDTO();

                proprietarioDTO.setProprietarioId(cursor.getInt(cursor.getColumnIndex("proprietario_id")));
                proprietarioDTO.setProprietarioIdServidor(cursor.getInt(cursor.getColumnIndex("proprietario_id_servidor")));
                proprietarioDTO.setNomeCompleto(cursor.getString(cursor.getColumnIndex("nome_completo")));
                proprietarioDTO.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
                proprietarioDTO.setRg(cursor.getString(cursor.getColumnIndex("rg")));
                proprietarioDTO.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
                proprietarioDTO.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                proprietarioDTO.setDataNascimento(cursor.getString(cursor.getColumnIndex("data_nascimento")));
                proprietarioDTO.setNumeroCnh(cursor.getString(cursor.getColumnIndex("numero_cnh")));

                EnderecoProprietarioDTO enderecoProprietarioDTO = new EnderecoProprietarioDTO();
                enderecoProprietarioDTO.setEnderecoId(cursor.getInt(cursor.getColumnIndex("endereco_id")));
                enderecoProprietarioDTO.setCep(cursor.getString(cursor.getColumnIndex("cep")));
                enderecoProprietarioDTO.setComplemento(cursor.getString(cursor.getColumnIndex("complemento")));
                enderecoProprietarioDTO.setLogradouro(cursor.getString(cursor.getColumnIndex("logradouro")));
                enderecoProprietarioDTO.setComplemento(cursor.getString(cursor.getColumnIndex("complemento")));
                enderecoProprietarioDTO.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
                enderecoProprietarioDTO.setBairro(cursor.getString(cursor.getColumnIndex("bairro")));
                enderecoProprietarioDTO.setEstado(cursor.getString(cursor.getColumnIndex("estado")));
                enderecoProprietarioDTO.setNumero(cursor.getString(cursor.getColumnIndex("numero")));

                proprietarioDTO.setEnderecoProprietarioDTO(enderecoProprietarioDTO);
            }

            cursor.close();
        }

        return proprietarioDTO;
    }

    // buscar proprietario pelo e-mail
    public ProprietarioDTO buscarProprietarioPeloEmail(String email) {
        ProprietarioDTO proprietarioDTO = null;

        return proprietarioDTO;
    }

    // buscar proprietário pelo rg
    public ProprietarioDTO buscarProprietarioPeloRg(String rg) {
        ProprietarioDTO proprietarioDTO = null;

        return proprietarioDTO;
    }

    // buscar proprietáiro pelo número da cnh
    public ProprietarioDTO buscarProprietarioPeloNumeroCnh(String numeroCnh) {
        ProprietarioDTO proprietarioDTO = null;

        return proprietarioDTO;
    }

    // filtrar proprietários
    public List<ProprietarioDTO> filtrar(String queryFiltro) {
        List<ProprietarioDTO> proprietarios = new ArrayList<>();

        Cursor cursor = this.bancoDados.rawQuery(queryFiltro, null);

        if (cursor != null) {

            while (cursor.moveToNext()) {
                ProprietarioDTO proprietarioDTO = new ProprietarioDTO();

                proprietarioDTO.setProprietarioId(cursor.getInt(cursor.getColumnIndex("proprietario_id")));
                proprietarioDTO.setProprietarioIdServidor(cursor.getInt(cursor.getColumnIndex("proprietario_id_servidor")));
                proprietarioDTO.setNomeCompleto(cursor.getString(cursor.getColumnIndex("nome_completo")));
                proprietarioDTO.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
                proprietarioDTO.setRg(cursor.getString(cursor.getColumnIndex("rg")));
                proprietarioDTO.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
                proprietarioDTO.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                proprietarioDTO.setDataNascimento(cursor.getString(cursor.getColumnIndex("data_nascimento")));
                proprietarioDTO.setNumeroCnh(cursor.getString(cursor.getColumnIndex("numero_cnh")));

                proprietarios.add(proprietarioDTO);
            }

            cursor.close();
        }

        return proprietarios;
    }

}
