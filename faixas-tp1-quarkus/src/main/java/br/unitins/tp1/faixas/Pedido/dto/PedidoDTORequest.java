package br.unitins.tp1.faixas.Pedido.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public record PedidoDTORequest (

      Long idUsuario,

      LocalDateTime data,
     
     List<ItemPedidoDTORequest> listaItemPedido,

    @NotNull (message = "valor total not must be null") 
     Double valorTotal
    
)
{}
