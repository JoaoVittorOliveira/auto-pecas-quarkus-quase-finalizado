package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Pedido;

public record PedidoResponseDTO(
    Long id,
    ClienteResponseDTO cliente,
    Double total,
    String StatusPagamentoPedido,
    List<ItemPedidoResponseDTO> itens
) 
    {
        public static PedidoResponseDTO valueOf(Pedido pedido){
            List<ItemPedidoResponseDTO> lista = pedido.getItens()
                                                .stream()
                                                .map(ItemPedidoResponseDTO::valueOf)
                                                .toList();

            return new PedidoResponseDTO(
                pedido.getId(), 
                ClienteResponseDTO.valueOf(pedido.getCliente()),
                pedido.getValorTotal(),
                pedido.getStatusPagamentoPedido().getDescricao(),
                lista);
        }
    }
