package br.unitins.topicos1.dto;

import java.time.LocalDateTime;

import br.unitins.topicos1.model.Pagamento;

public record PagamentoResponseDTO(
    Long id,
    Long idCliente,
    String nomeCliente,
    Long idPedido,
    Double valorTotalPedido,
    Double valorPago,
    Double troco,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao
) {
    public static PagamentoResponseDTO valueOf(Pagamento pg){
        return new PagamentoResponseDTO(
                    pg.getId(), 
                    pg.getCliente().getId(), 
                    pg.getCliente().getPessoa().getNome() + " " + pg.getCliente().getPessoa().getSobreNome(), 
                    pg.getPedido().getId(), 
                    pg.getPedido().getValorTotal(), 
                    pg.getValorPago(), 
                    pg.getTroco(),
                    pg.getDataCadastro(), 
                    pg.getDataAlteracao()
                   );
    }
}
