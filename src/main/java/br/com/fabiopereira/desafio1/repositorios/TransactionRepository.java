package br.com.fabiopereira.desafio1.repositorios;

import br.com.fabiopereira.desafio1.dominio.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
