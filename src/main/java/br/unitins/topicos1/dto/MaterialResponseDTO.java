package br.unitins.topicos1.dto;

import java.time.LocalDateTime;

import br.unitins.topicos1.model.Material;

public record MaterialResponseDTO(
        Long id,
        String nome,
        LocalDateTime dataCadastro,
        LocalDateTime dataAlteracao
) {
    public static MaterialResponseDTO valueOf(Material material){
        return new MaterialResponseDTO(
                material.getId(),
                material.getNome(),
                material.getDataCadastro(),
                material.getDataAlteracao()
        );
    }
}
