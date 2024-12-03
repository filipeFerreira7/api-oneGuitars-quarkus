package br.unitins.tp1.faixas.Especificacao.dto;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EspecificacaoDTORequest(
    @NotBlank(message = "O campo SKU é obrigatório")
    @Size(max=16)
    String sku,
    @NotNull()
    Double comprimento,
    @NotBlank(message = "O tipo de madeira deve ser informado.")
    String tipoMadeira,
    @NotBlank(message="O tipo de captador deve ser informado.")
    String tipoCaptador,
    @NotBlank(message = "O tipo de chave deve ser informado")
    String tipoChave
) 
{}
