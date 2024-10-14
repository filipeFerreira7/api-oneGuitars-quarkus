package br.unitins.tp1.faixas.Usuario.dto;

import br.unitins.tp1.faixas.Usuario.model.Sexo;
import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteDTORequest(

    @NotBlank(message="The field name must be filled")
    @Size(max=60)
    String nome,

    @NotBlank(message="The field CPF must be filled")
    @Size(max=11)
    String cpf,

    

    @NotBlank(message= "The field email must be filled")
    @Size(max=100)
    String email,

    @NotNull
    Long idTelefone,

    Sexo sexo
)
{   
    
}
