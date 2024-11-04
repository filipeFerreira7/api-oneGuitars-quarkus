package br.unitins.tp1.faixas.Pedido.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
// 
import java.util.List;

import br.unitins.tp1.faixas.Cliente.repository.ClienteRepository;
import br.unitins.tp1.faixas.Guitarra.repository.GuitarraRepository;
import br.unitins.tp1.faixas.ItemPedido.dto.ItemPedidoDTORequest;
import br.unitins.tp1.faixas.ItemPedido.model.ItemPedido;
import br.unitins.tp1.faixas.Lote.repository.LoteRepository;
import br.unitins.tp1.faixas.Lote.service.LoteService;
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

  @Inject
  public ClienteRepository clienteRepository;

  @Inject
  public LoteRepository loteRepository;

  @Inject
  public GuitarraRepository guitarraRepository;

  @Inject
  public LoteService loteService;

  @Override
  public Pedido findById(Long id) {
      return pedidoRepository.findById(id);
  }

  @Override   
  public List<Pedido> findByUsername(String username){
     // return pedidoRepository.findByUsername(username);

     return null;

     //Pausa em pedidos
  }
  

  @Override
  @Transactional
  public Pedido create(PedidoDTORequest dto) {
    Pedido pedido = new Pedido();

    pedido.setDataCompra(LocalDateTime.now());
    pedido.setCliente(clienteRepository.findById(dto.idCliente()));
    pedido.setValorTotal(0d);

    
    List<ItemPedido> item = new ArrayList<ItemPedido>();
    

    for(ItemPedidoDTORequest itemDTO : dto.listaItemPedido()){
      
      ItemPedido itemUnidade = new ItemPedido();
      

      itemUnidade.setLote(loteRepository.findById(itemDTO.idLote()));
      itemUnidade.setQuantidade(itemDTO.quantidade());
      itemUnidade.setPreco(itemDTO.preco());

      item.add(itemUnidade);

      
    }
      pedido.setListaItemPedido(item);
     pedidoRepository.persist(pedido);
     return pedido;
  }

  
}
