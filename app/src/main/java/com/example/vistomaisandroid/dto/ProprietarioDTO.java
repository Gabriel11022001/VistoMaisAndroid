package com.example.vistomaisandroid.dto;

public class ProprietarioDTO {

    private int proprietarioId;
    private String nomeCompleto;
    private String cpf;
    private String rg;
    private String email;
    private String telefone;
    private String dataNascimento;
    private String numeroCnh;
    private EnderecoProprietarioDTO enderecoProprietarioDTO = new EnderecoProprietarioDTO();

    public ProprietarioDTO() {}

    public ProprietarioDTO(int proprietarioId, String nomeCompleto, String cpf, String rg, String email, String telefone, String dataNascimento, String numeroCnh, EnderecoProprietarioDTO enderecoProprietarioDTO) {

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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {

        return this.email;
    }

    public void setDataNascimento(String dataNascimento) {
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

    public void setEnderecoProprietarioDTO(EnderecoProprietarioDTO enderecoProprietarioDTO) {
        this.enderecoProprietarioDTO = enderecoProprietarioDTO;
    }

    public EnderecoProprietarioDTO getEnderecoProprietarioDTO() {

        return this.enderecoProprietarioDTO;
    }

    // sobreescrevendo o método toString
    @Override
    public String toString() {

        return "Id: " + this.getProprietarioId() + " | nome completo: " + this.getNomeCompleto() + " | telefone: " + this.getTelefone()
                + " | email: " + this.getEmail() + " | data de nascimento: " + this.getDataNascimento() + " | número da cnh: " + this.getNumeroCnh();
    }

}
