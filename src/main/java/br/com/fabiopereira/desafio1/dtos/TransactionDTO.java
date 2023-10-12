package br.com.fabiopereira.desafio1.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderId, Long receiverId) {
}
