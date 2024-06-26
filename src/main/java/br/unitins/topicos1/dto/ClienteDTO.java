package br.unitins.topicos1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteDTO(

    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    String nome,

    @NotBlank(message = "O sobrenome não pode ser nulo ou vazio")
    String sobreNome,
    
    @NotBlank(message = "O cpf não pode ser nulo ou vazio")
    String cpf,

    @NotBlank
    Integer diaNasc,

    @NotBlank
    Integer mesNasc,

    @NotBlank
    Integer anoNasc,

    @NotBlank(message = "O username não pode ser nulo ou vazio")
    @Size(min = 4, max = 60, message = "O tamanho do username deve ser entre 4 e 60 caracteres.")
    String username,

    @NotBlank(message = "A senha não pode ser nula ou vazia")
    @Size(min = 6, max = 60, message = "O tamanho da senha deve ser entre 6 e 60 caracteres.")
    String senha,

    @Email
    String email
) {}
