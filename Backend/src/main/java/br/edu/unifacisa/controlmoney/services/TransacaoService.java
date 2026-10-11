package br.edu.unifacisa.controlmoney.services;

import br.edu.unifacisa.controlmoney.entidades.Transacao;
import br.edu.unifacisa.controlmoney.repositorios.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;

    public List<Transacao> listarTodas() {
        return repository.findAll();
    }

    public Optional<Transacao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Transacao salvar(Transacao transacao) {
        return repository.save(transacao);
    }

    public Transacao atualizar(Long id, Transacao transacaoNova) {
        Optional<Transacao> transacaoAntiga = repository.findById(id);
        if (transacaoAntiga.isPresent()) {
            Transacao transacao = transacaoAntiga.get();
            if (transacaoNova.getDescricao() != null) {
                transacao.setDescricao(transacaoNova.getDescricao());
            }
            if (transacaoNova.getValor() != null) {
                transacao.setValor(transacaoNova.getValor());
            }
            if (transacaoNova.getTipo() != null) {
                transacao.setTipo(transacaoNova.getTipo());
            }
            return repository.save(transacao);
        }
        throw new RuntimeException("Transação não encontrada");
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}