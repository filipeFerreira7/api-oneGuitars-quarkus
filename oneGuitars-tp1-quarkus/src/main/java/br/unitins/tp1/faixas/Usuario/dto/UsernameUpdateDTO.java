package br.unitins.tp1.faixas.Usuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsernameUpdateDTO(
    @NotBlank
    @Size(min = 6, max = 60, message = "O campo de username deve ter no minimo 6 caracteres e no máximo 60 ")
    String novoUsername
) {

}
