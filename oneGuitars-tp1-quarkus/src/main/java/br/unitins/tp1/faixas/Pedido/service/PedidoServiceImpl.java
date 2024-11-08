package br.unitins.tp1.faixas.Pedido.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
// 
import java.util.List;

import br.unitins.tp1.faixas.Cidade.service.CidadeService;
import br.unitins.tp1.faixas.Cliente.repository.ClienteRepository;
import br.unitins.tp1.faixas.Cliente.service.ClienteService;
import br.unitins.tp1.faixas.EnderecoEntrega.model.EnderecoEntrega;
import br.unitins.tp1.faixas.EnderecoEntrega.service.EnderecoEntregaService;
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
public class PedidoServiceImpl implements PedidoService {

  @Inject
  public PedidoRepository pedidoRepository;

  @Inject
  public ClienteRepository clienteRepository;

  @Inject
  public ClienteService clienteService;
  

  @Inject
  public EnderecoEntregaService enderecoEntregaService;

  @Inject
  public LoteRepository loteRepository;

  @Inject
  public GuitarraRepository guitarraRepository;

  @Inject
  public LoteService loteService;

  @Inject
  public CidadeService cidadeService;

  @Override
  public Pedido findById(Long id) {
    return pedidoRepository.findById(id);
  }

  @Override
  public List<Pedido> findByUsername(String username) {
   return pedidoRepository.findByUsername(username);


    // Pausa em pedidos
  }

  @Override
  @Transactional
  public Pedido create(PedidoDTORequest dto) {
        
    EnderecoEntrega end = new EnderecoEntrega();
    end.setBairro(dto.endereco().bairro());
    end.setCep(dto.endereco().cep());
    end.setCidade(cidadeService.findById(dto.endereco().idCidade()));
    end.setLogradouro(dto.endereco().logradouro());

    Pedido pedido = new Pedido();
  

    pedido.setDataCompra(LocalDateTime.now());
    pedido.setCliente(clienteRepository.findById(dto.idCliente()));
    pedido.setValorTotal(dto.valorTotal());
    pedido.setEndereco(end);


    List<ItemPedido> item = new ArrayList<ItemPedido>();

    for (ItemPedidoDTORequest itemDTO : dto.listaItemPedido()) {

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
