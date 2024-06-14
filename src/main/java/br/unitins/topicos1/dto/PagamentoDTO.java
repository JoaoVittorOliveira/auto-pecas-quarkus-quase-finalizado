package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record PagamentoDTO(
    @NotBlank
    Long idCliente,
    @NotBlank
    Long idPedido,
    @NotBlank
    Double valorPago
) {}
