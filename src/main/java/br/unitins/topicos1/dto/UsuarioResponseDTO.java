package br.unitins.topicos1.dto;

import java.time.LocalDateTime;

import br.unitins.topicos1.model.Pessoa;

public record UsuarioResponseDTO(
    Long id,
    String username,
    String nome,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao
) {
    public static UsuarioResponseDTO valueof(Pessoa pessoa){
        return new UsuarioResponseDTO(
            pessoa.getId(),
            pessoa.getUsuario().getUsername(),
            pessoa.getNome(),
            pessoa.getDataCadastro(),
            pessoa.getDataAlteracao()
        );
    }
}
