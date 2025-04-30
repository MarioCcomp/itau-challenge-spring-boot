package com.example.demo.controllers;


import com.example.demo.model.EstatisticaDTO;
import com.example.demo.model.Transacao;
import com.example.demo.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@RestController
public class TransacaoController {

    URI location = URI.create("/transacao");

    @Autowired
    private TransacaoService service;

    @GetMapping("/mock")
    public void mock() {
        this.mockTransacao();
    }

    @PostMapping("/transacao")
    public ResponseEntity<?> criarTransacao(@RequestBody Transacao transacao) {
        service.criar(transacao);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/estatistica")
    public ResponseEntity<EstatisticaDTO> estatistica() {
        DoubleSummaryStatistics stat = service.estatisticas();
        return ResponseEntity.ok(EstatisticaDTO.from(stat));
    }

    @DeleteMapping("/transacao")
    private ResponseEntity<Void> limparTransacoes() {
        service.deleteAll();
        return ResponseEntity.ok().build();
    }

    private void mockTransacao() {
        Transacao transacao = new Transacao(124.3, OffsetDateTime.now());
        Transacao transacao2 = new Transacao(250.3, OffsetDateTime.now());
        service.criar(transacao);
        service.criar(transacao2);
    }


}
