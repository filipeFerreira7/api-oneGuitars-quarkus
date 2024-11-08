package br.unitins.tp1.faixas.Pedido.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.tp1.faixas.EnderecoEntrega.dto.EnderecoEntregaDTOResponse;
import br.unitins.tp1.faixas.ItemPedido.dto.ItemPedidoDTOResponse;
import br.unitins.tp1.faixas.Pedido.model.Pedido;
public record PedidoDTOResponse(
    Long id,
    String nomeCliente,
    LocalDateTime dataCompra,
    Double valorTotal,
    List<ItemPedidoDTOResponse> listaItemPedido,
    EnderecoEntregaDTOResponse endereco
) {
 public static PedidoDTOResponse valueOf(Pedido pedido){
        return new PedidoDTOResponse(pedido.getId(),
                                       pedido.getCliente().getPessoaFisica().getNome(),
                                       pedido.getDataCompra(),
                                       pedido.getValorTotal(),
                                       pedido.getListaItemPedido().stream().map(i -> ItemPedidoDTOResponse.valueOf(i)).toList(),
                                       EnderecoEntregaDTOResponse.valueOf(pedido.getEndereco())
                                      );
    }
}
