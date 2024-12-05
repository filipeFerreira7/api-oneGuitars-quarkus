package br.unitins.tp1.faixas.Pedido.service;

import java.util.List;

import br.unitins.tp1.faixas.Pagamento.dto.BoletoDTOResponse;
import br.unitins.tp1.faixas.Pagamento.dto.CartaoCreditoDTORequest;
import br.unitins.tp1.faixas.Pedido.dto.PedidoDTORequest;
import br.unitins.tp1.faixas.Pedido.dto.PedidoDTOResponse;
import br.unitins.tp1.faixas.Pedido.model.Pedido;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;

@ApplicationScoped
public interface PedidoService {
    Pedido findById(Long id);
    List<Pedido> findByUsername(String username);
    List<PedidoDTOResponse> findByStatus(Integer idStatus);
    Pedido create(@Valid PedidoDTORequest dto);
    void delete(Long id);
    void updateStatusPedido(Long idPedido, Integer idStatus);
    BoletoDTOResponse gerarInfoBoleto(Long idPedido);
    void pagamentoBoleto(Long idPedido, Long idBoleto);
    void pagamentoCartao(Long id, CartaoCreditoDTORequest cartao);
    void cancelarPedidosExpirados();
    void devolucao(Pedido pedido);
}
