package com.example.vistomaisandroid.model;

public class Usuario {

    private int usuarioId;
    private String nomeCompleto;
    private String email;
    private String senha;
    private boolean ativo;
    private String token;
    private String cpf;

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getUsuarioId() {

        return this.usuarioId;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeCompleto() {

        return this.nomeCompleto;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {

        return this.email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {

        return this.senha;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getAtivo() {

        return this.ativo;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {

        return this.cpf;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {

        return this.token;
    }

}
