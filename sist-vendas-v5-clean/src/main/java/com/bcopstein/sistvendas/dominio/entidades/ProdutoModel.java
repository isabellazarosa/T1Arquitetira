package com.bcopstein.sistvendas.dominio.entidades;

public class ProdutoModel{
    private long id;
    private String descricao;
    private double precoUnitario;


    public ProdutoModel(long id, String descricao, double precoUnitario) {
        this.id = id;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
    }

    public long getId() {
        return this.id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public double getPrecoUnitario() {
        return this.precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public String toString() {
        return "{" +
            " codigo='" + getId() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", precoUnitario='" + getPrecoUnitario() + "'" +
            "}";
    }
}