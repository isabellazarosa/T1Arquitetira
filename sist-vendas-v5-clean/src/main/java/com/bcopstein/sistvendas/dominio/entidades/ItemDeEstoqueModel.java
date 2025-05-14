package com.bcopstein.sistvendas.dominio.entidades;

public class ItemDeEstoqueModel{
    private long id;
    private ProdutoModel produto;
    private int quantidade;
    private int estoqueMin;
    private int estoqueMax;

    public ItemDeEstoqueModel(long id, ProdutoModel produto, int quantidade, int estoqueMin, int estoqueMax) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.estoqueMin = estoqueMin;
        this.estoqueMax = estoqueMax;
    }

    public long getId() {
        return id;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getEstoqueMin() {
        return estoqueMin;
    }

    public int getEstoqueMax() {
        return estoqueMax;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setEstoqueMin(int estoqueMin) {
        this.estoqueMin = estoqueMin;
    }

    public void setEstoqueMax(int estoqueMax) {
        this.estoqueMax = estoqueMax;
    }

    @Override
    public String toString() {
        return "ItemDeEstoque [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", estoqueMin="
                + estoqueMin + ", estoqueMax=" + estoqueMax + "]";
    }
}