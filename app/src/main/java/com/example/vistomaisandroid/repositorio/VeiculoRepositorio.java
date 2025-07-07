package com.example.vistomaisandroid.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.vistomaisandroid.dto.CategoriaVeiculoDTO;
import com.example.vistomaisandroid.dto.ProprietarioDTO;
import com.example.vistomaisandroid.dto.VeiculoDTO;

import java.util.ArrayList;
import java.util.List;

public class VeiculoRepositorio extends RepositorioBase<VeiculoDTO> {

    public VeiculoRepositorio(Context contextoApp) throws Exception {
        super(contextoApp);
    }

    // cadastrar veiculo na base de dados
    @Override
    public void cadastrar(VeiculoDTO entidadeCadastrar) {
        ContentValues contentValuesCadastroVeiculo = new ContentValues();

        contentValuesCadastroVeiculo.put("marca", entidadeCadastrar.getMarca());
        contentValuesCadastroVeiculo.put("modelo", entidadeCadastrar.getModelo());
        contentValuesCadastroVeiculo.put("ano_lancamento", entidadeCadastrar.getAnoFabricacao());
        contentValuesCadastroVeiculo.put("ano_modelo", entidadeCadastrar.getAnoModelo());
        contentValuesCadastroVeiculo.put("cor", entidadeCadastrar.getCor());
        contentValuesCadastroVeiculo.put("numero_chassi", entidadeCadastrar.getNumeroChassi());
        contentValuesCadastroVeiculo.put("renavam", entidadeCadastrar.getRenavam());
        contentValuesCadastroVeiculo.put("proprietario_id", entidadeCadastrar.getProprietarioId());
        contentValuesCadastroVeiculo.put("categoria_veiculo_id", entidadeCadastrar.getCategoriaVeiculoId());

        this.bancoDados.insert("tb_veiculos", null, contentValuesCadastroVeiculo);
    }

    @Override
    public void editar(VeiculoDTO entidadeEditar) {

    }

    // listar todos os veiculos
    @Override
    public List<VeiculoDTO> listar() {
        List<VeiculoDTO> veiculos = new ArrayList<>();
        String query = "SELECT v.*, c.nome_categoria, p.nome_completo, p.cpf," +
                "c.telefone, c.rg, c.email, c.data_nascimento, c.numero_cnh " +
                "FROM tb_veiculos AS v, tb_categorias_veiculos AS c, tb_proprietarios AS p " +
                "WHERE v.proprietario_id = p.proprietario_id " +
                "AND v.categoria_veiculo_id = c.categoria_veiculo_id " +
                "ORDER BY v.marca ASC";

        Cursor cursor = this.bancoDados.rawQuery(query, null);

        if (cursor != null) {

            while (cursor.moveToNext()) {
                VeiculoDTO veiculoDTO = new VeiculoDTO();
                CategoriaVeiculoDTO categoriaVeiculoDTO = new CategoriaVeiculoDTO();
                ProprietarioDTO proprietarioDTO = new ProprietarioDTO();

                veiculoDTO.setVeiculoId(cursor.getInt(cursor.getColumnIndex("veiculo_id")));
                veiculoDTO.setMarca(cursor.getString(cursor.getColumnIndex("marca")));
                veiculoDTO.setModelo(cursor.getString(cursor.getColumnIndex("modelo")));
                veiculoDTO.setAnoModelo(cursor.getInt(cursor.getColumnIndex("ano_modelo")));
                veiculoDTO.setAnoFabricacao(cursor.getInt(cursor.getColumnIndex("ano_lancamento")));
                veiculoDTO.setCor(cursor.getString(cursor.getColumnIndex("cor")));
                veiculoDTO.setNumeroChassi(cursor.getString(cursor.getColumnIndex("numero_chassi")));
                veiculoDTO.setRenavam(cursor.getString(cursor.getColumnIndex("renavam")));
                veiculoDTO.setCategoriaVeiculoId(cursor.getInt(cursor.getColumnIndex("categoria_veiculo_id")));
                veiculoDTO.setProprietarioId(cursor.getInt(cursor.getColumnIndex("proprietario_id")));
                veiculoDTO.setPlaca(cursor.getString(cursor.getColumnIndex("placa")));

                categoriaVeiculoDTO.setCategoriaVeiculoId(cursor.getInt(cursor.getColumnIndex("categoria_veiculo_id")));
                categoriaVeiculoDTO.setNomeCategoria(cursor.getString(cursor.getColumnIndex("nome_categoria")));

                veiculoDTO.setCategoriaVeiculoDTO(categoriaVeiculoDTO);

                proprietarioDTO.setProprietarioId(cursor.getInt(cursor.getColumnIndex("proprietario_id")));
                proprietarioDTO.setNomeCompleto(cursor.getString(cursor.getColumnIndex("nome_completo")));
                proprietarioDTO.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
                proprietarioDTO.setRg(cursor.getString(cursor.getColumnIndex("rg")));
                proprietarioDTO.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                proprietarioDTO.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
                proprietarioDTO.setDataNascimento(cursor.getString(cursor.getColumnIndex("data_nascimento")));
                proprietarioDTO.setNumeroCnh(cursor.getString(cursor.getColumnIndex("numero_cnh")));

                veiculoDTO.setProprietarioDTO(proprietarioDTO);

                veiculos.add(veiculoDTO);
            }

            cursor.close();
        }

        return veiculos;
    }

    @Override
    public VeiculoDTO buscarPeloId(Integer idEntidadeBuscar) {

        return null;
    }

}
