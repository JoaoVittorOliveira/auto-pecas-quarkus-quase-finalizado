package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VolanteDTO (
        @NotBlank(message = "O nome nao pode ser null ou vazio")
        @Size(min = 4, max = 60, message = "Informe um nome valido (entre 4 e 60 caracteres)")
        String nome,
        @NotBlank(message = "O codigo nao pode ser null ou vazio")
        @Size(min = 16, max = 16, message = "O codigo do produto deve possuir 16 caracteres")
        String codigo,
        @NotBlank(message = "O codigo nao pode ser null ou vazio")
        Integer estoque,
        @NotBlank(message = "O preco do volante nao pode ser null ou vazio")
        Double preco,
        @NotBlank(message = "O diametro nao pode ser null ou vazio")
        String diametro,
        @NotBlank(message = "A cor nao pode ser null ou vazio")
        Long idCor,
        @NotBlank(message = "O formato nao pode ser null ou vazio")
        Long idFormato,
        @NotBlank(message = "O material nao pode ser null ou vazio")
        Long idMaterial
) { }