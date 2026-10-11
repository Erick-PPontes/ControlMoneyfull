package br.edu.unifacisa.controlmoney.services;

import br.edu.unifacisa.controlmoney.entidades.Conta;
import br.edu.unifacisa.controlmoney.repositorios.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    public List<Conta> listarTodas() {
        return repository.findAll();
    }

    public Optional<Conta> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Conta salvar(Conta conta) {
        return repository.save(conta);
    }

    public Conta atualizar(Long id, Conta contaNova) {
        Optional<Conta> contaAntiga = repository.findById(id);
        if (contaAntiga.isPresent()) {
            Conta conta = contaAntiga.get();
            if (contaNova.getTitular() != null) {
                conta.setTitular(contaNova.getTitular());
            }
            if (contaNova.getSaldo() != null) {
                conta.setSaldo(contaNova.getSaldo());
            }
            return repository.save(conta);
        }
        throw new RuntimeException("Conta não encontrada");
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}