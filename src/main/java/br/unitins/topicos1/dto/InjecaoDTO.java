package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record InjecaoDTO(
        @NotBlank(message = "O nome nao pode ser null ou vazio")
        @Size(min = 3, max = 60, message = "Informe um nome valido (entre 3 e 60 caracteres)")
        String nome,
        @NotBlank(message = "O codigo nao pode ser null ou vazio")
        @Size(min = 3, max = 60, message = "Informe um codigo valida ")
        String codigo,
        Integer estoque,
        Double preco,
        @NotBlank(message = "O código da cor nao pode ser null ou vazio")
        @Size(min = 3, max = 20, message = "Informe um tipo de combustivel valido")
        String tipoCombustivel
) { }
