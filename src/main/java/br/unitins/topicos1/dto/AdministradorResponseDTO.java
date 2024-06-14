package br.unitins.topicos1.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.unitins.topicos1.model.Administrador;

public record AdministradorResponseDTO(
    Long id,
    Long idUsuario,
    String nome,
    String sobreNome,
    String cpf,
    LocalDate dataNascimento,
    String username,
    String senha,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao
) {
    public static AdministradorResponseDTO valueOf(Administrador administrador){
        return new AdministradorResponseDTO(
                    administrador.getId(),
                    administrador.getPessoa().getUsuario().getId(),
                    administrador.getPessoa().getNome(),
                    administrador.getPessoa().getSobreNome(),
                    administrador.getPessoa().getCpf(),
                    administrador.getPessoa().getDataNascimento(),
                    administrador.getPessoa().getUsuario().getUsername(),
                    administrador.getPessoa().getUsuario().getSenha(),
                    administrador.getDataCadastro(),
                    administrador.getDataAlteracao()
                );
    }
}
