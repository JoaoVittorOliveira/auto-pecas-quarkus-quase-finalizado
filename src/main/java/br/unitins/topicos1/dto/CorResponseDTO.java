package br.unitins.topicos1.dto;

import java.time.LocalDateTime;

import br.unitins.topicos1.model.Cor;

public record CorResponseDTO(
        Long id,
        String nome,
        String codigo,
        LocalDateTime dataCadastro,
        LocalDateTime dataAlteracao
) {
    public static CorResponseDTO valueOf(Cor cor) {
        return new CorResponseDTO(
                cor.getId(),
                cor.getNome(),
                cor.getCodigo(),
                cor.getDataCadastro(),
                cor.getDataAlteracao()
        );
    }
}
