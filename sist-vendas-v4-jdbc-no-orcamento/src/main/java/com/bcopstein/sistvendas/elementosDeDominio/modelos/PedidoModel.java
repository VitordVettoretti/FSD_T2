package com.bcopstein.sistvendas.elementosDeDominio.modelos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PedidoModel {
    private long id;
    private List<ItemPedidoModel> itens;

    public PedidoModel(long id) {
        this.id = id;
        this.itens = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public List<ItemPedidoModel> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public void addItem(ItemPedidoModel item){
        itens.add(item);
    }
}
