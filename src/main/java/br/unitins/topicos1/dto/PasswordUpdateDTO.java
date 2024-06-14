package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record PasswordUpdateDTO(
    @NotBlank
    String oldPassword,
    @NotBlank
    String newPassword
) {
}
