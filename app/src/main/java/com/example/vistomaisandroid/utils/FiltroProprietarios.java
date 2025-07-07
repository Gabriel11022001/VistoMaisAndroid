package com.example.vistomaisandroid.utils;

import android.os.Parcel;

import androidx.annotation.NonNull;

import com.example.vistomaisandroid.dto.ProprietarioDTO;
import com.example.vistomaisandroid.repositorio.ProprietarioRepositorio;

import java.util.Collections;
import java.util.List;

public class FiltroProprietarios extends Filtro<ProprietarioDTO> {

    private String nomeCompleto;
    private String cpf;
    private String rg;
    private String email;
    private String telefone;
    private String numeroCnh;
    private ProprietarioRepositorio proprietarioRepositorio;

    public FiltroProprietarios(ProprietarioRepositorio proprietarioRepositorio) {
        this.proprietarioRepositorio = proprietarioRepositorio;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto.trim();
    }

    public String getNomeCompleto() {

        return this.nomeCompleto;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone.trim();
    }

    public String getTelefone() {

        return this.telefone;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getEmail() {

        return this.email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf.trim();
    }

    public String getCpf() {

        return this.cpf;
    }

    public void setRg(String rg) {
        this.rg = rg.trim();
    }

    public String getRg() {

        return this.rg.trim();
    }

    public void setNumeroCnh(String numeroCnh) {
        this.numeroCnh = numeroCnh.trim();
    }

    public String getNumeroCnh() {

        return this.numeroCnh;
    }

    // filtrar os proprietários na base local do app
    @Override
    public List<ProprietarioDTO> filtrar() {
        String query = "SELECT p.*, e.cep, e.complemento, e.logradouro, e.cidade, e.bairro, e.estado, e.numero," +
                "e.endereco_id " +
                "FROM tb_proprietarios AS p INNER JOIN tb_enderecos AS e " +
                "ON p.proprietario_id = e.proprietario_id " +
                "AND p.nome_completo LIKE '%" + this.getNomeCompleto() + "%' " +
                "AND p.cpf LIKE '%" + this.getCpf() + "%' " +
                "ORDER BY p.nome_completo ASC";

        return Collections.unmodifiableList(this.proprietarioRepositorio.filtrar(query));
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

    }

}
