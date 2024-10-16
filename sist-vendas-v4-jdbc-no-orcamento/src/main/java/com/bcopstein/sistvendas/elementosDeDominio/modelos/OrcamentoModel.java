package com.bcopstein.sistvendas.elementosDeDominio.modelos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrcamentoModel {
    private long id;
    private List<ItemPedidoModel> itens;
    private double custoItens;
    private double imposto;
    private double desconto;
    private double custoConsumidor;
    private boolean efetivado;

    public OrcamentoModel(long id) {
        this.id = id;
        this.itens = new ArrayList<>();
        this.efetivado = false;
    }

    public OrcamentoModel() {
        this.itens = new ArrayList<>();
        this.efetivado = false;
    }

    public void addItensPedido(PedidoModel pedido){
        for(ItemPedidoModel itemPedido:pedido.getItens()){
            itens.add(itemPedido);
        }
    }

    public List<ItemPedidoModel> getItens(){
        return Collections.unmodifiableList(itens);
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
        efetivado = true;
    }
}
