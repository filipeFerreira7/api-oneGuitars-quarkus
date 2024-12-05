package br.unitins.tp1.faixas.Pedido.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record StatusPedidoDTORequest 
   (
    @NotNull(message = "O id do status não pode ser nulo")
    @Min(value = 1, message = "O valor deve estar entre 1 e 8")
    @Max(value = 8, message = "O valor deve estar entre 1 e 8")
    Integer idStatus
) {
    
}

