package com.bcopstein.sistvendas.dominio.entidades;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PedidoModel {
    private long id;
    private List<ItemPedidoModel> itens;
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

    public PedidoModel(long id) {
        this.id = id;
        this.itens = new LinkedList<>();
    }

    public long getId() {
        return id;
    }

    public List<ItemPedidoModel> getItens() {
        return new ArrayList<ItemPedidoModel>(itens);
    }

    public void addItem(ItemPedidoModel item){
        itens.add(item);
    }
}
