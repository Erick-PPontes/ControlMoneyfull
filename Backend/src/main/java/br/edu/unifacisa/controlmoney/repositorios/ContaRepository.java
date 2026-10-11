package br.edu.unifacisa.controlmoney.repositorios;

import br.edu.unifacisa.controlmoney.entidades.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}