package com.example.vistomaisandroid.dto;

public class VeiculoDTO {

    private int veiculoId;
    private String marca;
    private String modelo;
    private int anoFabricacao;
    private int anoModelo;
    private String renavam;
    private String numeroChassi;
    private String placa;
    private String cor;
    private int proprietarioId;
    private int categoriaVeiculoId;

    public void setVeiculoId(int veiculoId) {
        this.veiculoId = veiculoId;
    }

    public int getVeiculoId() {

        return this.veiculoId;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMarca() {

        return this.marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getModelo() {

        return this.modelo;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public int getAnoFabricacao() {

        return this.anoFabricacao;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public int getAnoModelo() {

        return this.anoModelo;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public String getRenavam() {

        return this.renavam;
    }

    public void setNumeroChassi(String numeroChassi) {
        this.numeroChassi = numeroChassi;
    }

    public String getNumeroChassi() {

        return this.numeroChassi;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {

        return this.placa;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getCor() {

        return this.cor;
    }

    public void setCategoriaVeiculoId(int categoriaVeiculoId) {
        this.categoriaVeiculoId = categoriaVeiculoId;
    }

    public int getCategoriaVeiculoId() {

        return this.categoriaVeiculoId;
    }

    public void setProprietarioId(int proprietarioId) {
        this.proprietarioId = proprietarioId;
    }

    public int getProprietarioId() {

        return this.proprietarioId;
    }

}

