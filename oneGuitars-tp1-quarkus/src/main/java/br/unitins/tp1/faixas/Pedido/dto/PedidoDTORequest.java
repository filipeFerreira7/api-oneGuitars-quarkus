package br.unitins.tp1.faixas.Pedido.dto;

import java.util.List;

import br.unitins.tp1.faixas.Endereco.dto.EnderecoDTORequest;
import br.unitins.tp1.faixas.Pedido.ItemPedido.dto.ItemPedidoDTORequest;

public record PedidoDTORequest (

      Long idUsuario,

      EnderecoDTORequest endereco,
     
     List<ItemPedidoDTORequest> listaItemPedido
    
)
{}
