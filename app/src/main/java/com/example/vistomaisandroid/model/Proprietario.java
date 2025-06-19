package com.example.vistomaisandroid.model;

public class Proprietario {

    private int proprietarioId;
    private String nomeCompleto;
    private String email;
    private String cpf;
    private String rg;
    private String telefone;
    private String dataNascimento;
    private String numeroCnh;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String numero;

    public Proprietario() {}

    public Proprietario(
            int proprietarioId,
            String nomeCompleto,
            String email,
            String cpf,
            String telefone,
            String rg,
            String dataNascimento,
            String numeroCnh,
            String cep,
            String logradouro,
            String complemento,
            String bairro,
            String cidade,
            String estado,
            String numero
    ) {
        this.setProprietarioId(proprietarioId);
        this.setNomeCompleto(nomeCompleto);
        this.setTelefone(telefone);
        this.setCpf(cpf);
        this.setEmail(email);
        this.setRg(rg);
        this.setDataNasciment(dataNascimento);
        this.setNumeroCnh(numeroCnh);
        this.setCep(cep);
        this.setLogradouro(logradouro);
        this.setComplemento(complemento);
        this.setBairro(bairro);
        this.setCidade(cidade);
        this.setNumero(numero);
        this.setEstado(estado);
    }

    public void setProprietarioId(int proprietarioId) {
        this.proprietarioId = proprietarioId;
    }

    public int getProprietarioId() {

        return this.proprietarioId;
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

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {

        return this.cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRg() {

        return this.rg;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {

        return this.telefone;
    }

    public void setDataNasciment(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDataNascimento() {

        return this.dataNascimento;
    }

    public void setNumeroCnh(String numeroCnh) {
        this.numeroCnh = numeroCnh;
    }

    public String getNumeroCnh() {

        return this.numeroCnh;
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


