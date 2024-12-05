package br.unitins.tp1.oneGuitars.Pedido.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.tp1.oneGuitars.Endereco.dto.EnderecoDTOResponse;
import br.unitins.tp1.oneGuitars.Pagamento.model.Pagamento;
import br.unitins.tp1.oneGuitars.Pedido.ItemPedido.dto.ItemPedidoDTOResponse;
import br.unitins.tp1.oneGuitars.Pedido.model.Pedido;
public record PedidoDTOResponse(
    Long id,
    String nomeUsuario,
    LocalDateTime dataCompra,
    Double valorTotal,
    List<ItemPedidoDTOResponse> listaItemPedido,
    List<StatusPedidoDTOResponse> status,
    LocalDateTime tempoPagamento,
    Pagamento pagamento,
    EnderecoDTOResponse endereco
) {
 public static PedidoDTOResponse valueOf(Pedido pedido){
        return new PedidoDTOResponse(pedido.getId(),
                                       pedido.getUsuario().getPessoaFisica().getNome(),
                                       pedido.getDataCompra(),
                                       pedido.getValorTotal(),
                                       pedido.getListaItemPedido().stream().map(i -> ItemPedidoDTOResponse.valueOf(i)).toList(),
                                       pedido.getListaStatus().stream().map(StatusPedidoDTOResponse::valueOf).toList(),
                                       pedido.getTempoPagamento(),
                                       pedido.getPagamento(),
                                       EnderecoDTOResponse.valueOf(pedido.getEndereco())
                                      );
    }
}
