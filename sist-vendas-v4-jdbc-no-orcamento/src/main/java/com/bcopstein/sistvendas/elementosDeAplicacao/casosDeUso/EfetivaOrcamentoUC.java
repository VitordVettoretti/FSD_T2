package com.bcopstein.sistvendas.elementosDeAplicacao.casosDeUso;

import org.springframework.stereotype.Component;

import com.bcopstein.sistvendas.RelacaoExterna.WEB.dtos.OrcamentoDTO;
import com.bcopstein.sistvendas.elementosDeDominio.modelos.OrcamentoModel;
import com.bcopstein.sistvendas.elementosDeDominio.servicos.ServicoDeVendas;

@Component
public class EfetivaOrcamentoUC {
    private ServicoDeVendas servicoDeVendas;
    
    public EfetivaOrcamentoUC(ServicoDeVendas servicoDeVendas){
        this.servicoDeVendas = servicoDeVendas;
    }

    public OrcamentoDTO run(long idOrcamento){
        OrcamentoModel orcamento =  servicoDeVendas.efetivaOrcamento(idOrcamento);
        return OrcamentoDTO.fromModel(orcamento);
    }
}