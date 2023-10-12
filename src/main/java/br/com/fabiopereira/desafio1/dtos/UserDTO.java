package br.com.fabiopereira.desafio1.dtos;

import br.com.fabiopereira.desafio1.dominio.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
