package br.unitins.tp1.faixas.Pedido.service;
import java.time.LocalDateTime;
// 
import java.util.List;

import br.unitins.tp1.faixas.Pedido.dto.PedidoDTORequest;
import br.unitins.tp1.faixas.Pedido.model.Pedido;
import br.unitins.tp1.faixas.Pedido.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class PedidoServiceImpl implements PedidoService{

  
  @Inject
  public PedidoRepository pedidoRepository;

  @Override
  public Pedido findById(Long id) {
      return pedidoRepository.findById(id);
  }
  
  @Override   
  public List<Pedido> findByDataCompra(LocalDateTime dataCompra){
      return pedidoRepository.findByDataCompra(dataCompra);
  }
  
  @Override
  public List<Pedido> findAll() {
    return pedidoRepository.findAll().list();
  }

  @Override
  @Transactional
  public Pedido create(PedidoDTORequest dto) {
    Pedido pedido = new Pedido();
    pedido.setDataCompra(dto.dataCompra());
    pedido.setValorTotal(dto.valorTotal());
     pedidoRepository.persist(pedido);
     return pedido;
  }

  @Override
  @Transactional
  public Pedido update(Long id, PedidoDTORequest dto) {
        Pedido pedido = pedidoRepository.findById(id);
        pedido.setDataCompra(dto.dataCompra());
        pedido.setValorTotal(dto.valorTotal());
         pedidoRepository.persist(pedido);
         return pedido;
    }
  

  @Override
  @Transactional
  public void delete(Long id) {
      pedidoRepository.deleteById(id);
  }
  
}
