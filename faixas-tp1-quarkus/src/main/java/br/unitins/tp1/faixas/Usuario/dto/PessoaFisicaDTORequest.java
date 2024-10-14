package br.unitins.tp1.faixas.Usuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PessoaFisicaDTORequest(
    @NotBlank(message = "The field nome must be filled")
    String nome,
    @NotBlank(message="The field CPF must be filled")
    @Size(max=11)
    String cpf,

    Integer idSexo


   
)
{}
