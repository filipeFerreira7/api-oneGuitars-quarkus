package br.unitins.tp1.faixas.Endereco.dto;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record EnderecoDTORequest 
(
    @NotBlank(message = "o campo logradouro não pode ser nulo")
    String logradouro,

    @NotBlank(message = "o campo bairro não pode ser nulo")
    String bairro,

    @NotBlank(message = "o campo cep não pode ser nulo")
    String cep,

    @NotNull
    @Min(1)
    Long idCidade
)
{}
