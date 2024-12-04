package br.unitins.tp1.faixas.Pedido.dto;

import java.util.List;

import br.unitins.tp1.faixas.Endereco.dto.EnderecoDTORequest;
import br.unitins.tp1.faixas.Pedido.ItemPedido.dto.ItemPedidoDTORequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PedidoDTORequest (
      @NotNull
      @Min(1)
      Long idUsuario,
      @Valid()
      EnderecoDTORequest endereco,
     @Valid()
     List<ItemPedidoDTORequest> listaItemPedido
    
)
{}
