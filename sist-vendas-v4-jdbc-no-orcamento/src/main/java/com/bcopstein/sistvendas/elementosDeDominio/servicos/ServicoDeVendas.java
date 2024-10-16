package com.bcopstein.sistvendas.elementosDeDominio.servicos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bcopstein.sistvendas.elementosDeDominio.modelos.ItemPedidoModel;
import com.bcopstein.sistvendas.elementosDeDominio.modelos.OrcamentoModel;
import com.bcopstein.sistvendas.elementosDeDominio.modelos.PedidoModel;
import com.bcopstein.sistvendas.elementosDeDominio.modelos.ProdutoModel;
import com.bcopstein.sistvendas.elementosDeDominio.persistencia.IEstoqueRepositorio;
import com.bcopstein.sistvendas.elementosDeDominio.persistencia.IOrcamentoRepositorio;

@Service
public class ServicoDeVendas {
    private IOrcamentoRepositorio orcamentos;
    private IEstoqueRepositorio estoque;
    private ServicoDeEstoque servicoEstoque;

    public ServicoDeVendas(IOrcamentoRepositorio orcamentos,IEstoqueRepositorio estoque,ServicoDeEstoque servicoEstoque){
        this.orcamentos = orcamentos;
        this.estoque = estoque;
        this.servicoEstoque = servicoEstoque;
    }
     
    public List<ProdutoModel> produtosDisponiveis() {
        return estoque.todosComEstoque();
    }

    public OrcamentoModel recuperaOrcamentoPorId(long id) {
        return this.orcamentos.recuperaPorId(id);
    }

    public OrcamentoModel criaOrcamento(PedidoModel pedido) {
        var novoOrcamento = new OrcamentoModel();
        novoOrcamento.addItensPedido(pedido);
        double custoItens = novoOrcamento.getItens().stream()
            .mapToDouble(it->it.getProduto().getPrecoUnitario()*it.getQuantidade())
            .sum();
        novoOrcamento.setImposto(custoItens * 0.1);
        if (novoOrcamento.getItens().size() > 5){
                novoOrcamento.setDesconto(custoItens * 0.05);
            }else{
                novoOrcamento.setDesconto(0.0);
            }
        novoOrcamento.setCustoConsumidor(custoItens + novoOrcamento.getImposto() - novoOrcamento.getDesconto());
        return this.orcamentos.cadastra(novoOrcamento);
    }
 
    public OrcamentoModel efetivaOrcamento(long id) {
        // Recupera o orçamento
        var orcamento = this.orcamentos.recuperaPorId(id);
        if (orcamento == null){
            throw new IllegalArgumentException("Orcamento não encontrado");
        }
        var ok = true;
        // Verifica se tem quantidade em estoque para todos os itens
        for (ItemPedidoModel itemPedido:orcamento.getItens()) {
            int qtdade = estoque.quantidadeEmEstoque(itemPedido.getProduto().getId());
            if (qtdade < itemPedido.getQuantidade()) {
                ok = false;
                break;
            }
        }
        // Se tem quantidade para todos os itens, da baixa no estoque para todos
        if (ok) {
            for (ItemPedidoModel itemPedido:orcamento.getItens()) {
                int qtdade = estoque.quantidadeEmEstoque(itemPedido.getProduto().getId());
                if (qtdade >= itemPedido.getQuantidade()) {
                    servicoEstoque.baixaEstoque(itemPedido.getProduto().getId(), itemPedido.getQuantidade());
                }
            }
            // Marca o orcamento como efetivado
            orcamento.efetiva();
            orcamentos.salva(orcamento);
        }
        // Retorna o orçamento marcado como efetivado ou não conforme disponibilidade do estoque
        return orcamentos.recuperaPorId(id);
    }
}
