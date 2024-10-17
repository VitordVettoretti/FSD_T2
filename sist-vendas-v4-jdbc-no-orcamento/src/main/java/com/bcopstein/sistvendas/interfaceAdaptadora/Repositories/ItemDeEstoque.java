package com.bcopstein.sistvendas.interfaceAdaptadora.Repositories;

import com.bcopstein.sistvendas.interfaceAdaptadora.Repositories.Produto;
import com.bcopstein.sistvendas.elementosDeDominio.modelos.ItemDeEstoqueModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemDeEstoque {  
    @Id  
    private long id;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Produto produto;
    private int quantidade;
    private int estoqueMin;
    private int estoqueMax;

    public ItemDeEstoque(long id, Produto produto, int quantidade, int estoqueMin, int estoqueMax) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.estoqueMin = estoqueMin;
        this.estoqueMax = estoqueMax;
    }

    protected ItemDeEstoque(){}

    public long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getEstoqueMin() {
        return estoqueMin;
    }

    public int getEstoqueMax() {
        return estoqueMax;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setEstoqueMin(int estoqueMin) {
        this.estoqueMin = estoqueMin;
    }

    public void setEstoqueMax(int estoqueMax) {
        this.estoqueMax = estoqueMax;
    }

    @Override
    public String toString() {
        return "ItemDeEstoque [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", estoqueMin="
                + estoqueMin + ", estoqueMax=" + estoqueMax + "]";
    }

    public static ItemDeEstoqueModel toItemDeEstoqueModel(ItemDeEstoque item) {
        return new ItemDeEstoqueModel(item.getId(), Produto.toProdutoModel(item.getProduto()), item.getQuantidade(), item.getEstoqueMin(), item.getEstoqueMax());
    }

    public static ItemDeEstoque toItemDeEstoque(ItemDeEstoqueModel item) {
        return new ItemDeEstoque(item.getId(), Produto.fromProdutoModel(item.getProduto()), item.getQuantidade(), item.getEstoqueMin(), item.getEstoqueMax());
    }
}