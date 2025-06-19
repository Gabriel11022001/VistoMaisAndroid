package com.example.vistomaisandroid.dto;

public class EnderecoProprietarioDTO {

    private int enderecoId;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String numero;

    public EnderecoProprietarioDTO() {}

    public EnderecoProprietarioDTO(int enderecoId, String cep, String complemento, String logradouro, String bairro, String cidade, String estado, String numero) {
        this.setEnderecoId(enderecoId);
        this.setCep(cep);
        this.setLogradouro(logradouro);
        this.setComplemento(complemento);
        this.setBairro(bairro);
        this.setCidade(cidade);
        this.setNumero(numero);
        this.setEstado(estado);
    }

    public void setEnderecoId(int enderecoId) {
        this.enderecoId = enderecoId;
    }

    public int getEnderecoId() {

        return this.enderecoId;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCep() {

        return this.cep;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getLogradouro() {

        return this.logradouro;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getComplemento() {

        return this.complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getBairro() {

        return this.bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCidade() {

        return this.cidade;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumero() {

        return this.numero;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {

        return this.estado;
    }

}
