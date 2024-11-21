package br.unitins.tp1.faixas.Estado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstadoDTORequest(
    @NotBlank(message = "The field name must be filled")
    @Size(max=60, message = "O nome deve possuir no máximo 60 caracteres")
    String nome,
    @NotBlank(message="The field sigla is required")
    @Size(min = 2, max = 2, message = "A sigla deve possuir dois caracteres")
    String sigla
) {
     
}
