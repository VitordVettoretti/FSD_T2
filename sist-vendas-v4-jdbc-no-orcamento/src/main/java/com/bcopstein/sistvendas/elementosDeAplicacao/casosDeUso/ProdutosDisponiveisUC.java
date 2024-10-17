package com.bcopstein.sistvendas.elementosDeAplicacao.casosDeUso;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcopstein.sistvendas.RelacaoExterna.WEB.dtos.ProdutoDTO;
import com.bcopstein.sistvendas.elementosDeDominio.servicos.ServicoDeEstoque;

@Component
public class ProdutosDisponiveisUC {
    private ServicoDeEstoque servicoEstoque;

    public ProdutosDisponiveisUC(ServicoDeEstoque servicoEstoque){
        this.servicoEstoque = servicoEstoque;
    }

    public List<ProdutoDTO> run(){
        return servicoEstoque.produtosDisponiveis().stream()
            .map(ProdutoDTO::fromModel)
            .toList();
    }
}
