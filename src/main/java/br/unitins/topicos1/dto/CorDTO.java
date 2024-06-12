package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CorDTO(
        @NotBlank(message = "O nome da cor nao pode ser null ou vazio")
        @Size(min = 3, max = 60, message = "Informe um nome valido (entre 3 e 60 caracteres)")
        String nome,
        @NotBlank(message = "O código da cor nao pode ser null ou vazio")
        @Size(min = 6, max = 6, message = "Informe um codigo com 6 caracteres")
        String codigo
) { }
