package br.unitins.topicos1.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.unitins.topicos1.model.Cliente;

public record ClienteResponseDTO(
    Long id,
    Long idUsuario,
    String nomeCompleto,
    String cpf,
    LocalDate dataNascimento,
    String email,
    String username,
    String senha,
    Double saldo,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao
) {
    public static ClienteResponseDTO valueOf(Cliente cliente){
        return new ClienteResponseDTO(
                    cliente.getId(),
                    cliente.getPessoa().getUsuario().getId(),
                    cliente.getPessoa().getNome() + " " + cliente.getPessoa().getSobreNome(),
                    cliente.getPessoa().getCpf(),
                    cliente.getPessoa().getDataNascimento(),
                    cliente.getEmail(),
                    cliente.getPessoa().getUsuario().getUsername(),
                    cliente.getPessoa().getUsuario().getSenha(),
                    cliente.getSaldo(),
                    cliente.getDataCadastro(),
                    cliente.getDataAlteracao()
                );
    }
}
