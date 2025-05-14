package com.bcopstein.sistvendas.dominio.entidades;

public class ItemPedidoModel {
    private ProdutoModel produto;
    private int quantidade;
    
    public ItemPedidoModel(ProdutoModel produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ItemPedido [produto=" + produto + ", quantidade=" + quantidade + "]";
    }
}
