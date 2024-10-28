package br.unitins.tp1.faixas.Pedido.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.tp1.faixas.Pedido.model.Pedido;
import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTOResponse;
public record PedidoDTOResponse(
    Long id,
    UsuarioDTOResponse user,
    LocalDateTime dataCompra,
    Double valorTotal,
    List<ItemPedidoDTOResponse> listaItemPedido
) {
 public static PedidoDTOResponse valueOf(Pedido pedido){
        return new PedidoDTOResponse(pedido.getId(),
                                       UsuarioDTOResponse.valueOf(pedido.getUser()), 
                                       pedido.getDataCompra(),
                                       pedido.getValorTotal(),
                                       pedido.getListaItemPedido().stream().map(i -> ItemPedidoDTOResponse.valueOf(i)).toList()
                                      );
    }
}
