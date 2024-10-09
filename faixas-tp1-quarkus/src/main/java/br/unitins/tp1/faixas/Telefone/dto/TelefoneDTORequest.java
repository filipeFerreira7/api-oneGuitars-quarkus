package br.unitins.tp1.faixas.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TelefoneDTORequest(

    @NotBlank(message="The field name must be filled")
    @Size(max=60)
    String dd,

    @NotBlank(message="The field CPF must be filled")
    @Size(max=11)
    String numero
    
)
{   
    
}
