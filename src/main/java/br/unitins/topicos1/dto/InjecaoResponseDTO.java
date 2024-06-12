package br.unitins.topicos1.dto;

import java.time.LocalDateTime;

import br.unitins.topicos1.model.Injecao;

public record InjecaoResponseDTO(
        Long id,
        String nome,
        String codigo,
        Integer estoque,
        Double preco,
        String tipoCombustivel,
        LocalDateTime dataCadastro,
        LocalDateTime dataAlteracao
) {
    public static InjecaoResponseDTO valueOf(Injecao injecao) {
        return new InjecaoResponseDTO(
                injecao.getId(),
                injecao.getNome(),
                injecao.getCodigo(),
                injecao.getEstoque(),
                injecao.getPreco(),
                injecao.getTipoCombustivel(),
                injecao.getDataCadastro(),
                injecao.getDataAlteracao()
        );
    }
}
