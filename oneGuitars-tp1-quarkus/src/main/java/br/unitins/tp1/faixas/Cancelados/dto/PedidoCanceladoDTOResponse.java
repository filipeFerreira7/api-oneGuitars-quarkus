package br.unitins.tp1.faixas.Cancelados.dto;

import java.time.LocalDateTime;

import br.unitins.tp1.faixas.Cancelados.model.PedidoCancelado;
import br.unitins.tp1.faixas.Pedido.dto.PedidoDTOResponse;

public record PedidoCanceladoDTOResponse(
    Long id,

    LocalDateTime dataCancelamento,

    PedidoDTOResponse pedidoCancelado
)
{
 public static PedidoCanceladoDTOResponse valueOf(PedidoCancelado pedido){
    return new PedidoCanceladoDTOResponse(pedido.getId(),
                                pedido.getDataCancelamento(),
                                PedidoDTOResponse.valueOf(pedido.getPedidoCancelado())
    );

  }

}
