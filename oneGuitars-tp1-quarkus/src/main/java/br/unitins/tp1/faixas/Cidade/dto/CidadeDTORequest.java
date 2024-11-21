package br.unitins.tp1.faixas.Cidade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CidadeDTORequest(
        @NotBlank(message = "The field name must be filled")
         @Size(max = 60, message = "O nome deve conter no máximo 60 caracteres") 
         String nome,
        @NotNull(message = "idEstado not must be null") 
        Long idEstado) {

}
