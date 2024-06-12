package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Produto;

public record ProdutoResponseDTO(
    Long id,
    String nome,
    String codigo,
    Integer estoque,
    Double preco,
    String nomeImagem
) {
    public static ProdutoResponseDTO valueOf(Produto produto){
        return new ProdutoResponseDTO(
                        produto.getId(), 
                        produto.getNome(), 
                        produto.getCodigo(), 
                        produto.getEstoque(),
                        produto.getPreco(),
                        produto.getNomeImagem()
                    );
    }
}
