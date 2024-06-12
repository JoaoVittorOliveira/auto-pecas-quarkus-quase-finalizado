package br.unitins.topicos1.dto;

public record ProdutoDTO(
    String nome,
    String codigo,
    Integer estoque,
    Double preco
) {
    
}
