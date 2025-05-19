package com.bcopstein.sistvendas.dominio.entidades;
import jakarta.persistence.*;


@Embeddable
public class ItemPedidoModel {
    @ManyToOne
    @JoinColumn(name = "produto_id") 
    private ProdutoModel produto;
    private int quantidade;
    
    public ItemPedidoModel(ProdutoModel produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemPedidoModel() {

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
