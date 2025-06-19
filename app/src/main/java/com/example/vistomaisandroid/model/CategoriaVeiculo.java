package com.example.vistomaisandroid.model;

public class CategoriaVeiculo {

    private int categoriaVeiculoId;
    private String nomeCategoria;

    public void setCategoriaVeiculoId(int categoriaVeiculoId) {
        this.categoriaVeiculoId = categoriaVeiculoId;
    }

    public int getCategoriaVeiculoId() {

        return this.categoriaVeiculoId;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getNomeCategoria() {

        return this.nomeCategoria;
    }

}
