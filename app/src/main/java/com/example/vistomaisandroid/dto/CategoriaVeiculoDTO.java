package com.example.vistomaisandroid.dto;

public class CategoriaVeiculoDTO {

    private int categoriaVeiculoId;
    private String nomeCategoria;

    public CategoriaVeiculoDTO () {}

    public CategoriaVeiculoDTO(int categoriaVeiculoId, String nomeCategoria) {
        this.categoriaVeiculoId = categoriaVeiculoId;
        this.nomeCategoria = nomeCategoria;
    }

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
