package com.bcopstein.sistvendas.elementosDeDominio.persistencia;

import java.util.List;

import com.bcopstein.sistvendas.elementosDeDominio.modelos.OrcamentoModel;

public interface IOrcamentoRepositorio {
    List<OrcamentoModel> todos();
    OrcamentoModel cadastra(OrcamentoModel orcamento);
    OrcamentoModel recuperaPorId(long id);
    void salva(OrcamentoModel orcamento);
}
