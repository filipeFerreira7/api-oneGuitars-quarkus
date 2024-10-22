package br.unitins.tp1.faixas.Usuario.dto;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PessoaFisicaDTORequest(
    @NotBlank(message = "The field nome must be filled")
    String nome,
    @NotBlank(message="The field CPF must be filled")
    @Size(max=11)
    String cpf,
    
    @NotBlank(message= "The field email must be filled")
    @Size(max=100)
    String email,

    @NotNull
    @Min(1)
    Integer idSexo


   
)
{}
