package br.unitins.tp1.oneGuitars.Cancelados.dto;

import java.time.LocalDateTime;

import br.unitins.tp1.oneGuitars.Cancelados.model.PedidoCancelado;
import br.unitins.tp1.oneGuitars.Pedido.dto.PedidoDTOResponse;

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
