package br.unitins.tp1.faixas.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GuitarraDTORequest(
     @NotBlank(message= "The field name must be filled in")
    @Size(max=60)
    String nome,
    @NotNull (message= "numero de serie not must be null")
    String numeroSerie,
    @NotNull (message = "idEspecificacao not must be null")
    Long idEspecificacao
    
){

}
