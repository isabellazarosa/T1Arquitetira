package com.bcopstein.sistvendas.aplicacao.dtos;

public class ItemPedidoDTO {
    private long idProduto;
    private int qtdade;

    public ItemPedidoDTO(long idProduto, int qtdade) {
        this.idProduto = idProduto;
        this.qtdade = qtdade;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public int getQtdade() {
        return qtdade;
    }

    @Override
    public String toString() {
        return "ItemPedidoDTO [idProduto=" + idProduto + ", qtdade=" + qtdade + "]";
    }    
}
