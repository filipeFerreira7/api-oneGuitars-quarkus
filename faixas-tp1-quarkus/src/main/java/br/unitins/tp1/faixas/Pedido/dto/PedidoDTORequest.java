package br.unitins.tp1.faixas.Pedido.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record PedidoDTORequest (
         @NotNull(message= "The field data must be filled")
    LocalDateTime dataCompra,
    @NotNull (message = "valor total not must be null") 
     Double valorTotal
    
)
{}
