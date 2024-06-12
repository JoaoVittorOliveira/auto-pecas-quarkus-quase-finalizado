package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.ItemPedido;

public record ItemPedidoResponseDTO(
    Long id,
    String nome_produto,
    Double valor_item,
    String codigo_produto,
    Double porcentagem_desconto,
    Integer quantidade
) {
    public static ItemPedidoResponseDTO valueOf(ItemPedido item) {
        return new ItemPedidoResponseDTO(
            item.getId(), 
            item.getProduto().getNome(), 
            item.getValor(),
            item.getProduto().getCodigo(),
            item.getPorcentagemDesconto(),
            item.getQuantidadeProdutos());
    }
}
