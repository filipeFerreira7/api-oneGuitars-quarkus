package br.unitins.tp1.faixas.Administrador.dto;
import jakarta.validation.constraints.NotBlank;

public record AdministradorUsernameUpdateDTO(
    @NotBlank
    String newUsername
) {
}