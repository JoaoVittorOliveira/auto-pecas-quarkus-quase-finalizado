package br.unitins.topicos1.dto;

import java.time.LocalDateTime;

import br.unitins.topicos1.model.Formato;

public record FormatoResponseDTO(
        Long id,
        String descricaoFormato,
        LocalDateTime dataCadastro,
        LocalDateTime dataAlteracao
) {
    public  static FormatoResponseDTO valueOf(Formato formato){
        return new FormatoResponseDTO(
                formato.getId(),
                formato.getDescricaoFormato(),
                formato.getDataCadastro(),
                formato.getDataAlteracao()
        );
    }
}
