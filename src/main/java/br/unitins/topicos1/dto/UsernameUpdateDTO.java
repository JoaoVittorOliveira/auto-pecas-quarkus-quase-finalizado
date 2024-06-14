package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record UsernameUpdateDTO(
    @NotBlank
    String newUsername
) {
}
