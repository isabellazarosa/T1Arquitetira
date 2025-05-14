package com.bcopstein.sistvendas.aplicacao.dtos;

import java.util.LinkedList;
import java.util.List;

import com.bcopstein.sistvendas.dominio.entidades.ItemPedidoModel;
import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;



public class OrcamentoDTO {
    private long id;
    private List<ItemPedidoDTO> itens;
    private double custoItens;
    private double imposto;
    private double desconto;
    private double custoConsumidor;
    private boolean efetivado;

    public OrcamentoDTO(OrcamentoModel orcamento) {
        this.id = orcamento.getId();
        this.custoItens = orcamento.getCustoItens();
        this.imposto = orcamento.getImposto();
        this.desconto = orcamento.getDesconto();
        this.custoConsumidor = orcamento.getCustoConsumidor();
        if (orcamento.isEfetivado()) this.efetivado = true;
        else this.efetivado = false;
        itens = new LinkedList<>();
        for(ItemPedidoModel item:orcamento.getItens()){
            itens.add(new ItemPedidoDTO(item.getProduto().getId(), item.getQuantidade()));
        }
    }

    public long getId() {
        return id;
    }

    public List<ItemPedidoDTO> getItens(){
        return itens;
    }

    public double getCustoItens() {
        return custoItens;
    }

    public double getImposto() {
        return imposto;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getCustoConsumidor() {
        return custoConsumidor;
    }

    public boolean isEfetivado() {
        return efetivado;
    }

    public void efetiva(){
        efetivado = true;
    }

    public static OrcamentoDTO fromModel(OrcamentoModel orcamento){
        return new OrcamentoDTO(orcamento);
    }
}
