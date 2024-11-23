package br.unitins.tp1.faixas.Administrador.dto;


import jakarta.validation.constraints.NotBlank;

public record AdministradorPasswordUpdateDTO(
    
    @NotBlank
    String oldPassword,

    @NotBlank
    String newPassword
) {

}