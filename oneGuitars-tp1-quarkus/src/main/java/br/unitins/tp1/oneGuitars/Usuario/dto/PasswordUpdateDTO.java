package br.unitins.tp1.oneGuitars.Usuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PasswordUpdateDTO(
    @NotBlank
    @Size(min = 6, max = 60, message = "O campo de senha deve ter no minimo 6 caracteres e no máximo 60 ")
    String senhaAntiga,
    @NotBlank
    @Size(min = 6, max = 60, message = "O campo de senha deve ter no minimo 6 caracteres e no máximo 60 ")
    String senhaNova
)
{

}
