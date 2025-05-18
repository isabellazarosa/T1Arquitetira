package com.bcopstein.sistvendas.aplicacao.dtos;

public class EntradaEstoqueDTO {
    private String codigoProduto;
    private int quantidade;

    public EntradaEstoqueDTO() {}

    public EntradaEstoqueDTO(String codigoProduto, int quantidade) {
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

