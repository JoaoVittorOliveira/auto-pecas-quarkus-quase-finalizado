package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FormatoDTO(
        @NotBlank(message = "Não pode ser null ou vazio")
        @Size(max = 80, message = "Quantidade de caracteres em excesso")
        String descricaoFormato
) { }
