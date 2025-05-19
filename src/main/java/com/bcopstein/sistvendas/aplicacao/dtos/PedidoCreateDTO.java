package com.bcopstein.sistvendas.aplicacao.dtos;

import com.bcopstein.sistvendas.aplicacao.dtos.ItemPedidoDTO;

import java.util.List;

public class PedidoCreateDTO {
    private String cliente;
    private String estado;
    private String pais;
    private List<ItemPedidoDTO> itensPedido;

    // Getters e setters
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<ItemPedidoDTO> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedidoDTO> itensPedido) {
        this.itensPedido = itensPedido;
    }
}
