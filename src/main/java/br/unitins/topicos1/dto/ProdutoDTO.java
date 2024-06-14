package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record ProdutoDTO(
    @NotBlank(message = "Insira um nome para o produto")
    String nome,
    @NotBlank(message = "Insira um codigo para o produto")
    String codigo,
    @NotBlank(message = "Insira uma quantidade no estoque para o produto")
    Integer estoque,
    @NotBlank(message = "Insira um preco para o produto")
    Double preco
) {
    
}
