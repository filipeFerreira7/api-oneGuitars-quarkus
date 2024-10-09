package br.unitins.tp1.faixas.DTO;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EspecificacaoDTORequest(
    @NotBlank(message = "The field sku must be filled")
    @Size(max=16)
    String sku,
    @NotNull()
    Double comprimento,
    @NotBlank(message = "The field tipo madeira must be filled")
    String tipoMadeira,
    @NotBlank(message="The field tipo captador must be filled")
    String tipoCaptador,
    @NotBlank(message = "The field tipo chave must be filled")
    String tipoChave
) 
{}
