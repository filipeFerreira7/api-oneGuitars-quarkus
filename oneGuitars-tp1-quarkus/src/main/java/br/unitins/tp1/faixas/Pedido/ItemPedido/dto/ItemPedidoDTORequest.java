package br.unitins.tp1.faixas.Pedido.ItemPedido.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemPedidoDTORequest (

     @NotNull(message = "A quantidade não pode ser nula")
    @Positive(message = "A quantidade não pode ser negativa ou 0")
    Integer quantidade,
    @NotNull(message = "O preço não pode ser nula")
    @Positive(message = "O preço não pode ser menor ou igual a 0")
    Double preco,
    @NotNull( message = "O id de lote não pode ser nulo")
    @Min(1)
    Long idLote
    
)
{}
