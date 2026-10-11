package br.edu.unifacisa.controlmoney.repositorios;

import br.edu.unifacisa.controlmoney.entidades.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}