package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ElementCollection
    private List<ItemPedido> itens;
    private double custoItens;
    private String cliente;
    private String pais;

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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    private String estado;
    private double imposto;
    private double desconto;
    private double custoConsumidor;
    private boolean efetivado;
    private LocalDate dataEfetivacao;

    public Orcamento(long id) {
        this.id = id;
        this.itens = new LinkedList<>();
        this.efetivado = false;
        this.dataEfetivacao = null;
    }

    public Orcamento(){
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

    public void addItensPedido(Pedido pedido){
        for(ItemPedido itemPedido:pedido.getItens()){
            itens.add(itemPedido);
        }
    }

    public List<ItemPedido> getItens(){
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

    public static Orcamento fromOrcamentoModel(OrcamentoModel orcModel){
        return new Orcamento(orcModel.getId());
    }

    public static OrcamentoModel toOrcamentoModel(Orcamento orc){
        return new OrcamentoModel(orc.getId());
    }
}
