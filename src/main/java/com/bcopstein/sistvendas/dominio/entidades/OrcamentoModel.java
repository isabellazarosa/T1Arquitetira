package com.bcopstein.sistvendas.dominio.entidades;

import java.util.LinkedList;
import java.util.List;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class OrcamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ElementCollection
    private List<ItemPedidoModel> itens;
    private double custoItens;
    private double imposto;
    private double desconto;
    private double custoConsumidor;
    private boolean efetivado;
    private LocalDate dataEfetivacao;

    public OrcamentoModel(long id) {
        this.id = id;
        this.itens = new LinkedList<>();
        this.efetivado = false;
        this.dataEfetivacao = null;
    }
     
    public OrcamentoModel(){
        this.itens = new LinkedList<>();
        this.efetivado = false;
        this.dataEfetivacao = null;
    }

    public LocalDate getDataEfetivacao() {
        return dataEfetivacao;
    }

    public void setDataEfetivacao(LocalDate dataEfetivacao) {
        this.dataEfetivacao = dataEfetivacao;
    }

    public void addItensPedido(PedidoModel pedido){
        for(ItemPedidoModel itemPedido:pedido.getItens()){
            itens.add(itemPedido);
        }
    }

    public List<ItemPedidoModel> getItens(){
        return itens;
    }

    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public double getCustoItens() {
        return custoItens;
    }

    public void setCustoItens(double custoItens){
        this.custoItens = custoItens;
    }

    //Calcula o valor total dos itens do orcamento baseado na lista atual de itens e seus precos
    public double valorTotalItens() {
        return itens.stream()
                .mapToDouble(item -> item.getProduto().getPrecoUnitario() * item.getQuantidade())
                .sum();
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto(double imposto){
        this.imposto = imposto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto){
        this.desconto = desconto;
    }

    public double getCustoConsumidor() {
        return custoConsumidor;
    }

    public void setCustoConsumidor(double custoConsumidor){
        this.custoConsumidor = custoConsumidor;
    }

    public boolean isEfetivado() {
        return efetivado;
    }

    public void efetiva(){
        this.efetivado = true;
        this.dataEfetivacao = LocalDate.now();
    }
}
