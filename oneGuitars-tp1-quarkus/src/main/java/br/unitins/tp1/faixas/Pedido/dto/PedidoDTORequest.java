package br.unitins.tp1.faixas.Pedido.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.tp1.faixas.ItemPedido.dto.ItemPedidoDTORequest;
import jakarta.validation.constraints.NotNull;

public record PedidoDTORequest (

      Long idCliente,

      LocalDateTime data,
     
     List<ItemPedidoDTORequest> listaItemPedido,

    @NotNull (message = "valor total not must be null") 
     Double valorTotal
    
)
{}
