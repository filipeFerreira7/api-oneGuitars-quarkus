package br.unitins.tp1.faixas.Estado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstadoDTORequest(
    @NotBlank(message = "The field name must be filled")
    @Size(max=60)
    String nome,
    @NotBlank(message="idEstado not must be null")
    String sigla
) {
     
}
