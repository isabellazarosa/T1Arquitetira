package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades;



import com.bcopstein.sistvendas.dominio.entidades.ProdutoModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Produto {
    @Id
    private long id;
    private String descricao;
    private double precoUnitario;

    protected Produto(){ }
    
    public Produto(long id, String descricao, double precoUnitario) {
        this.id = id;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
    }

    public long getId() {
        return this.id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public double getPrecoUnitario() {
        return this.precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public String toString() {
        return "{" +
            " codigo='" + getId() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", precoUnitario='" + getPrecoUnitario() + "'" +
            "}";
    }

    public static Produto fromProdutoModel(ProdutoModel pModel){
        return new Produto(pModel.getId(),pModel.getDescricao(),pModel.getPrecoUnitario());
    }

    public static ProdutoModel toProdutoModel(Produto prod){
        return new ProdutoModel(prod.getId(),prod.getDescricao(),prod.getPrecoUnitario());
    }
}
