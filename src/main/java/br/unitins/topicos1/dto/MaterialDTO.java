package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MaterialDTO(
   @NotBlank(message = "O nome nao pode ser null ou vazio")
   @Size(min = 3, max = 60, message = "Informe um nome valido (entre 3 e 60 caracteres)")
   String nome
) {}
