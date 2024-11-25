package br.unitins.tp1.faixas.Usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record UsernameUpdateDTO(
    @NotBlank
    String novoUsername
) {

}
