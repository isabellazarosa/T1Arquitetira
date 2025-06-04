package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Pedido {
    private long id;
    private List<ItemPedido> itens;
    private String cliente;

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    private String pais;
    private String estado;

    public Pedido(long id) {
        this.id = id;
        this.itens = new LinkedList<>();
    }

    public long getId() {
        return id;
    }

    public List<ItemPedido> getItens() {
        return new ArrayList<ItemPedido>(itens);
    }

    public void addItem(ItemPedido item){
        itens.add(item);
    }
}
