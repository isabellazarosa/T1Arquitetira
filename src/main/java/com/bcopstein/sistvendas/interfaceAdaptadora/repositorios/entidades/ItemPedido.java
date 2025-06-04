package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ItemPedido {
    @ManyToOne
    @JoinColumn(name = "produto_id") 
    private Produto produto;
    private int quantidade;
    
    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemPedido() {

    }

    public Produto getProduto() {
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
