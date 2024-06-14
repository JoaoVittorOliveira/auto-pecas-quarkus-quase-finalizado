package br.unitins.topicos1.dto;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;

public record ItemPedidoDTO(
    @NotNull
    Double procentagem_desconto,
    @NotBlank(message = "Deve haver quantidade de produtos selecionados")
    Integer quantidade_produtos,
    @NotBlank(message = "Deve haver produtos")
    Long idProduto
) {}
