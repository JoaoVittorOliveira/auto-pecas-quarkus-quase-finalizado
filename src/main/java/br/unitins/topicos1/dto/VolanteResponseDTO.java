package br.unitins.topicos1.dto;

import java.time.LocalDateTime;

import br.unitins.topicos1.model.Volante;

public record VolanteResponseDTO(
        Long id,
        String nome,
        String codigo,
        Integer estoque,
        Double preco,
        String diametro,
        LocalDateTime dataCadastro,
        LocalDateTime dataAlteracao,
        CorResponseDTO cor,
        FormatoResponseDTO formato,
        MaterialResponseDTO mateial

) {
    public static VolanteResponseDTO valueOf(Volante volante){
        return new VolanteResponseDTO(
                volante.getId(),
                volante.getNome(),
                volante.getCodigo(),
                volante.getEstoque(),
                volante.getPreco(),
                volante.getDiametro(),
                volante.getDataCadastro(),
                volante.getDataAlteracao(),
                CorResponseDTO.valueOf(volante.getCor()),
                FormatoResponseDTO.valueOf(volante.getFormato()),
                MaterialResponseDTO.valueOf(volante.getMateiral())
        );
    }
}
