package br.unitins.tp1.faixas.Pedido.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
// 
import java.util.List;
import java.util.UUID;

import br.unitins.tp1.faixas.Cidade.service.CidadeService;
import br.unitins.tp1.faixas.EnderecoEntrega.model.EnderecoEntrega;
import br.unitins.tp1.faixas.EnderecoEntrega.service.EnderecoEntregaService;
import br.unitins.tp1.faixas.Guitarra.repository.GuitarraRepository;
import br.unitins.tp1.faixas.Lote.repository.LoteRepository;
import br.unitins.tp1.faixas.Lote.service.LoteService;
import br.unitins.tp1.faixas.Pagamento.dto.BoletoDTOResponse;
import br.unitins.tp1.faixas.Pagamento.dto.CartaoCreditoDTORequest;
import br.unitins.tp1.faixas.Pagamento.model.Boleto;
import br.unitins.tp1.faixas.Pagamento.model.CartaoCredito;
import br.unitins.tp1.faixas.Pagamento.repository.PagamentoRepository;
import br.unitins.tp1.faixas.Pedido.ItemPedido.dto.ItemPedidoDTORequest;
import br.unitins.tp1.faixas.Pedido.dto.PedidoDTORequest;
import br.unitins.tp1.faixas.Pedido.model.ItemPedido;
import br.unitins.tp1.faixas.Pedido.model.Pedido;
import br.unitins.tp1.faixas.Pedido.repository.PedidoRepository;
import br.unitins.tp1.faixas.Usuario.model.Usuario;
import br.unitins.tp1.faixas.Usuario.repository.UsuarioRepository;
import br.unitins.tp1.faixas.Usuario.service.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {
  
  @Inject
  public PagamentoRepository pagamentoRepository;

  @Inject
  public PedidoRepository pedidoRepository;

  @Inject
  public UsuarioRepository usuarioRepository;

  @Inject
  public UsuarioService usuarioService;

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
  }

  @Override
  @Transactional
  public Pedido create(PedidoDTORequest dto) {
    //criando endereço
    EnderecoEntrega end = new EnderecoEntrega();
    end.setBairro(dto.endereco().bairro());
    end.setCep(dto.endereco().cep());
    end.setCidade(cidadeService.findById(dto.endereco().idCidade()));
    end.setLogradouro(dto.endereco().logradouro());

    //Criando pedido
    Pedido pedido = new Pedido();
    pedido.setDataCompra(LocalDateTime.now());
    Usuario usuario = usuarioRepository.findById(dto.idUsuario());
    pedido.setUsuario(usuario);

    //caclc valor total
    //todos os itens estão nessa variável
    pedido.setValorTotal(0d); // valor vai ser calculado

    List<ItemPedidoDTORequest> valorItens = dto.listaItemPedido();
    valorItens.forEach(t -> {
      pedido.setValorTotal(pedido.getValorTotal() + (t.quantidade() * t.preco()));
    });

    pedido.setEndereco(end);
    // prazo para efetuar o pagamento
    pedido.setTempoPagamento(LocalDateTime.now().plusMinutes(5));
  
    
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

  @Override
  @Transactional
  public BoletoDTOResponse gerarInfoBoleto(Long idPedido) {
    Pedido pedido = pedidoRepository.findById(idPedido);
    Double value = pedido.getValorTotal();

    Boleto boleto = new Boleto();
    boleto.setValor(value);
    boleto.setCodigo(UUID.randomUUID().toString());
    pagamentoRepository.persist(boleto);

    return BoletoDTOResponse.valueOf(boleto);
    
  }

  @Override
  @Transactional
  public void pagamentoBoleto(Long idPedido, Long idBoleto) {
      Pedido p = pedidoRepository.findById(idPedido);
      
      if(p ==null)
        throw new IllegalArgumentException("Não foi encontrado o pedido");
      
     p.setPagamento(pagamentoRepository.findById(idBoleto));
  }

  @Override
  @Transactional
  public void pagamentoCartao(Long idPedido, CartaoCreditoDTORequest cartaoDTO) {
      Pedido p = pedidoRepository.findById(idPedido);

      if(p == null)
        throw new IllegalArgumentException("Não foi encontrado o pedido");

    CartaoCredito c = CartaoCreditoDTORequest.converteCartaoCredito(cartaoDTO);
  
      //logica para descontar o saldo
      c.setValor(p.getValorTotal());

      if(c.getSaldoCartao()>= p.getValorTotal()){
        c.setSaldoCartao(c.getSaldoCartao()-p.getValorTotal());

          c.setEstaPago(true);
          
          LocalDateTime now = LocalDateTime.now();
          c.setDataPagamento(now);
      //verificando se o tempo não foi ultrapassado
          if(now.isAfter(p.getTempoPagamento())){
            throw new IllegalArgumentException("Tempo esgotado. Tente novamente");
      }
      p.setPagamento(c);
    }
    else{
      c.setEstaPago(false);
      throw new IllegalArgumentException("Saldo insuficiente no cartão");
    }
 
      pagamentoRepository.persist(c);
      p.setPagamento(c);  
  }
}
