package br.unitins.tp1.faixas.Pedido.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.tp1.faixas.EnderecoEntrega.dto.EnderecoEntregaDTORequest;
import br.unitins.tp1.faixas.Pedido.ItemPedido.dto.ItemPedidoDTORequest;

public record PedidoDTORequest (

      Long idCliente,

      EnderecoEntregaDTORequest endereco,

      LocalDateTime data,

      LocalDateTime tempoPagamento,
     
     List<ItemPedidoDTORequest> listaItemPedido
    
)
{}
