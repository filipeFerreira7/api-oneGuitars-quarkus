package br.unitins.tp1.oneGuitars.Telefone.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TelefoneDTORequest(

    @NotBlank(message="o dd é obrigatório")
    @Size(max=60)
    String dd,

    @NotBlank(message="o número é obrigatório")
    @Size(max=11)
    String numero
    
)
{   
    
}
