package br.unitins.topicos1.dto;

public record ItemPedidoDTO(
    Double procentagem_desconto,
    Integer quantidade_produtos,
    Long idProduto
) {}
