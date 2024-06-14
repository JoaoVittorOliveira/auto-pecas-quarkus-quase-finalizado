package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthUsuarioDTO(
    @NotBlank(message = "Insira um username - BEAN VALIDATION")
    String username,
    @NotBlank(message = "Insira uma senha - BEAN VALIDATION")
    String senha,
    int perfil
) {
    
}
