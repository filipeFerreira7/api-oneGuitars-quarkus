package br.unitins.tp1.oneGuitars.Pedido.dto;

import br.unitins.tp1.oneGuitars.Pedido.model.Status;
import br.unitins.tp1.oneGuitars.Pedido.model.StatusPedido;

public record StatusPedidoDTOResponse(
        Status status) {
    public static StatusPedidoDTOResponse valueOf(StatusPedido s) {
        return new StatusPedidoDTOResponse(
                s.getStatus());
    }
}
