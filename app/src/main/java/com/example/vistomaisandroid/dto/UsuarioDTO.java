package com.example.vistomaisandroid.dto;

public class UsuarioDTO {

    private int usuarioId;
    private String token;
    private String email;
    private String senha;
    private String nomeCompleto;
    private boolean ativo;

    public UsuarioDTO() {

    }

    public UsuarioDTO(int usuarioId, String token, String email, String senha, String nomeCompleto, Boolean ativo) {
        this.usuarioId = usuarioId;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
        this.token = token;
    }

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

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {

        return this.token;
    }

}
