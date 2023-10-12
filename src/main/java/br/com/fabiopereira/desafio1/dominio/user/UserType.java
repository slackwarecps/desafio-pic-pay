package br.com.fabiopereira.desafio1.dominio.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


public enum UserType {
  COMMON,
    MERCHANT
}
