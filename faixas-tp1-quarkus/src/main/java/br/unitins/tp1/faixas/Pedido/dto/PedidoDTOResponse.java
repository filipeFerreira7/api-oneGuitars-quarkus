package br.unitins.tp1.faixas.Pedido.dto;

import java.time.LocalDateTime;

import br.unitins.tp1.faixas.Pedido.model.Pedido;
public record PedidoDTOResponse(
    Long id,
    LocalDateTime dataCompra,
    Double valorTotal
) {
 public static PedidoDTOResponse valueOf(Pedido pedido){
        return new PedidoDTOResponse(pedido.getId(), 
                                       pedido.getDataCompra(), 
                                       pedido.getValorTotal()
                                      );
    }
}
