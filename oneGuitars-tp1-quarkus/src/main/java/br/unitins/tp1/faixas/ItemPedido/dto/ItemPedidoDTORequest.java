package br.unitins.tp1.faixas.ItemPedido.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ItemPedidoDTORequest (

     @NotNull(message = "A quantidade não pode ser nula")
    @Positive(message = "A quantidade não pode ser negativa ou 0")
    Integer quantidade,
    @NotNull(message = "O preço não pode ser nula")
    @Positive(message = "O preço não pode ser maior ou igual a 0")
    Double preco,
    @NotNull( message = "O id de lote não pode ser nulo")
    @PositiveOrZero(message = "O id não pode ser negativo ou 0")
    Long idLote
    
)
{}
