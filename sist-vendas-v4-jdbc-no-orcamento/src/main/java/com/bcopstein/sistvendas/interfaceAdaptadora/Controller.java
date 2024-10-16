package com.bcopstein.sistvendas.interfaceAdaptadora;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bcopstein.sistvendas.elementosDeAplicacao.casosDeUso.CriaOrcamentoUC;
import com.bcopstein.sistvendas.elementosDeAplicacao.casosDeUso.EfetivaOrcamentoUC;
import com.bcopstein.sistvendas.elementosDeAplicacao.casosDeUso.ProdutosDisponiveisUC;
import com.bcopstein.sistvendas.elementosDeAplicacao.dtos.ItemPedidoDTO;
import com.bcopstein.sistvendas.elementosDeAplicacao.dtos.OrcamentoDTO;
import com.bcopstein.sistvendas.elementosDeAplicacao.dtos.ProdutoDTO;

@RestController
public class Controller {
    private ProdutosDisponiveisUC produtosDisponiveis;
    private CriaOrcamentoUC criaOrcamento;
    private EfetivaOrcamentoUC efetivaOrcamento;

    public Controller(ProdutosDisponiveisUC produtosDisponiveis,
                      CriaOrcamentoUC criaOrcamento,
                      EfetivaOrcamentoUC efetivaOrcamento){
        this.produtosDisponiveis = produtosDisponiveis;
        this.criaOrcamento = criaOrcamento;
        this.efetivaOrcamento = efetivaOrcamento;
    }

    @GetMapping("")
    public String welcomeMessage(){
        return("Bem vindo as lojas ACME");
    }

    @GetMapping("produtosDisponiveis")
    public List<ProdutoDTO> produtosDisponiveis(){
        return produtosDisponiveis.run();
    }    

    @PostMapping("novoOrcamento")
    public OrcamentoDTO novoOrcamento(@RequestBody List<ItemPedidoDTO> itens){
        return criaOrcamento.run(itens);
    }

    @GetMapping("efetivaOrcamento/{id}")
    public OrcamentoDTO efetivaOrcamento(@PathVariable(value="id") long idOrcamento){
        return efetivaOrcamento.run(idOrcamento);
    }
}