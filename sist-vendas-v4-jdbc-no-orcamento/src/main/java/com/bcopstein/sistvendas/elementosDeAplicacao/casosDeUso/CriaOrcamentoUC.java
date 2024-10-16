package com.bcopstein.sistvendas.elementosDeAplicacao.casosDeUso;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcopstein.sistvendas.elementosDeAplicacao.dtos.ItemPedidoDTO;
import com.bcopstein.sistvendas.elementosDeAplicacao.dtos.OrcamentoDTO;
import com.bcopstein.sistvendas.elementosDeDominio.modelos.ItemPedidoModel;
import com.bcopstein.sistvendas.elementosDeDominio.modelos.OrcamentoModel;
import com.bcopstein.sistvendas.elementosDeDominio.modelos.PedidoModel;
import com.bcopstein.sistvendas.elementosDeDominio.modelos.ProdutoModel;
import com.bcopstein.sistvendas.elementosDeDominio.servicos.ServicoDeEstoque;
import com.bcopstein.sistvendas.elementosDeDominio.servicos.ServicoDeVendas;

@Component
public class CriaOrcamentoUC {
    private ServicoDeVendas servicoDeVendas;
    private ServicoDeEstoque servicoDeEstoque;
    
    public CriaOrcamentoUC(ServicoDeVendas servicoDeVendas,ServicoDeEstoque servicoDeEstoque){
        this.servicoDeVendas = servicoDeVendas;
        this.servicoDeEstoque = servicoDeEstoque;
    }

    public OrcamentoDTO run(List<ItemPedidoDTO> itens){
        PedidoModel pedido = new PedidoModel(0);
        for(ItemPedidoDTO item:itens){
            ProdutoModel produto = servicoDeEstoque.produtoPorCodigo(item.getIdProduto());
            ItemPedidoModel itemPedido = new ItemPedidoModel(produto, item.getQtdade());
            pedido.addItem(itemPedido);
        }
        OrcamentoModel orcamento = servicoDeVendas.criaOrcamento(pedido);
        return OrcamentoDTO.fromModel(orcamento);
    }
}
