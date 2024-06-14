package br.unitins.topicos1.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record PedidoDTO(
    @NotBlank(message = "Deve haver um cliente realizando o pedido")
    Long idCliente,
    @NotBlank
    List<ItemPedidoDTO> itens
) { }
