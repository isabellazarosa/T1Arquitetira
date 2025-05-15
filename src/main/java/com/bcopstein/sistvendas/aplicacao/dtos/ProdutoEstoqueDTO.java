package com.bcopstein.sistvendas.aplicacao.dtos;

/**
 * DTO que representa um produto do catálogo,
 * incluindo a quantidade atualmente disponível em estoque.
 */
public class ProdutoEstoqueDTO {
    private long id;
    private String descricao;
    private double precoUnitario;
    private int quantidadeDisponivel;

    public ProdutoEstoqueDTO(long id, String descricao, double precoUnitario, int quantidadeDisponivel) {
        this.id = id;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public static ProdutoEstoqueDTO fromModel(long id, String descricao, double precoUnitario, int qtd) {
        return new ProdutoEstoqueDTO(id, descricao, precoUnitario, qtd);
    }
}
