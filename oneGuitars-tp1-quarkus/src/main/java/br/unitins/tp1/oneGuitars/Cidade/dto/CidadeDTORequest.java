package br.unitins.tp1.oneGuitars.Cidade.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CidadeDTORequest(
        @NotBlank(message = "O nome da cidade precisa ser preenchido")
        @Size(max = 60, message = "O nome deve conter no máximo 60 caracteres") 
        String nome,

        @NotNull(message = "o Estado precisa ser referenciado e o mínimo valor é 1")
        @Min(1)
         Long idEstado){}
