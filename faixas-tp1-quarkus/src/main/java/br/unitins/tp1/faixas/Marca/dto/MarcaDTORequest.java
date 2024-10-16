package br.unitins.tp1.faixas.Marca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MarcaDTORequest(
     @NotBlank(message= "The field name must be filled")
    @Size(max=60)
    String nome
){

}
