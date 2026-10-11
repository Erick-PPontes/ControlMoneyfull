package br.edu.unifacisa.controlmoney.controllers;

import br.edu.unifacisa.controlmoney.entidades.Transacao;
import br.edu.unifacisa.controlmoney.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @GetMapping
    public ResponseEntity<List<Transacao>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transacao> buscarPorId(@PathVariable Long id) {
        Optional<Transacao> transacao = service.buscarPorId(id);
        if (transacao.isPresent()) {
            return ResponseEntity.ok(transacao.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Transacao> criar(@RequestBody Transacao transacao) {
        Transacao novaTransacao = service.salvar(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTransacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transacao> atualizar(@PathVariable Long id, @RequestBody Transacao transacao) {
        try {
            Transacao transacaoAtualizada = service.atualizar(id, transacao);
            return ResponseEntity.ok(transacaoAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}