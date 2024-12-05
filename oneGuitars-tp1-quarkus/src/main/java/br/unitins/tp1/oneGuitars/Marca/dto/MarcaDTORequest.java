package br.unitins.tp1.oneGuitars.Marca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MarcaDTORequest(
     @NotBlank(message= "O campo marca deve ser preenchido")
    @Size(max=60)
    String nome
){

}
