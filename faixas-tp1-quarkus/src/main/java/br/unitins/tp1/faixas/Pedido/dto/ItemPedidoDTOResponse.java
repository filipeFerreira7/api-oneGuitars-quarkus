package br.unitins.tp1.faixas.Pedido.dto;

import br.unitins.tp1.faixas.Lote.dto.LoteDTOResponse;
import br.unitins.tp1.faixas.Pedido.model.ItemPedido;
public record ItemPedidoDTOResponse(
    Long id,
    LoteDTOResponse lote,
    String nome,
    Integer quantidade,
    Double valor
) {
 public static ItemPedidoDTOResponse valueOf(ItemPedido itemPedido){
        return new ItemPedidoDTOResponse(
                                    itemPedido.getId(),
                                    LoteDTOResponse.valueOf(itemPedido.getLote()),
                                    itemPedido.getLote().getGuitarra().getNome(),
                                    itemPedido.getQuantidade(),
                                    itemPedido.getPreco()
                                      );
    }
}
