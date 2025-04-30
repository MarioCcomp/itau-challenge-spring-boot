package com.example.demo.services;


import com.example.demo.model.Transacao;
import com.example.demo.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class TransacaoService {

    private List<Transacao> transacoes = new ArrayList<>();


    public void criar(Transacao transacao) {
        if(transacao.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("Voce nao pode lancar transacoes futuras");
        }
        if(transacao.getValor() < 0) {
            throw new IllegalArgumentException("vc nao pode lancar uma transacao negativa");
        }
        this.transacoes.add(transacao);
    }

    public List<Transacao> findALl() {
        return this.transacoes;
    }

    public DoubleSummaryStatistics estatisticas() {

        DoubleSummaryStatistics stat = this.transacoes.stream().filter(t -> t.getDataHora().isAfter(OffsetDateTime.now().minusSeconds(60))).mapToDouble(Transacao::getValor).summaryStatistics();
        if(stat.getCount() == 0) {
            stat = new DoubleSummaryStatistics(0, 0, 0, 0);
            return stat;
        }
        return stat;
    }

    public void deleteAll() {
        this.transacoes.clear();
    }
}
