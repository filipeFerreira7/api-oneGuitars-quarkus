package br.unitins.tp1.faixas.Pedido.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.jboss.logging.Logger;

import br.unitins.tp1.faixas.Cancelados.model.PedidoCancelado;
import br.unitins.tp1.faixas.Cancelados.repository.PedidoCanceladoRepository;
import br.unitins.tp1.faixas.Cidade.service.CidadeService;
import br.unitins.tp1.faixas.Endereco.model.Endereco;
import br.unitins.tp1.faixas.Endereco.service.EnderecoService;
import br.unitins.tp1.faixas.Guitarra.repository.GuitarraRepository;
import br.unitins.tp1.faixas.Lote.model.Lote;
import br.unitins.tp1.faixas.Lote.repository.LoteRepository;
import br.unitins.tp1.faixas.Lote.service.LoteService;
import br.unitins.tp1.faixas.Pagamento.dto.BoletoDTOResponse;
import br.unitins.tp1.faixas.Pagamento.dto.CartaoCreditoDTORequest;
import br.unitins.tp1.faixas.Pagamento.model.Boleto;
import br.unitins.tp1.faixas.Pagamento.model.CartaoCredito;
import br.unitins.tp1.faixas.Pagamento.repository.PagamentoRepository;
import br.unitins.tp1.faixas.Pedido.ItemPedido.dto.ItemPedidoDTORequest;
import br.unitins.tp1.faixas.Pedido.dto.PedidoDTORequest;
import br.unitins.tp1.faixas.Pedido.dto.PedidoDTOResponse;
import br.unitins.tp1.faixas.Pedido.model.ItemPedido;
import br.unitins.tp1.faixas.Pedido.model.Pedido;
import br.unitins.tp1.faixas.Pedido.model.Status;
import br.unitins.tp1.faixas.Pedido.model.StatusPedido;
import br.unitins.tp1.faixas.Pedido.repository.ItemPedidoRepository;
import br.unitins.tp1.faixas.Pedido.repository.PedidoRepository;
import br.unitins.tp1.faixas.Pedido.resource.PedidoResource;
import br.unitins.tp1.faixas.Usuario.model.Usuario;
import br.unitins.tp1.faixas.Usuario.repository.UsuarioRepository;
import br.unitins.tp1.faixas.Usuario.service.UsuarioService;
import br.unitins.tp1.faixas.validation.EntidadeNotFoundException;
import br.unitins.tp1.faixas.validation.ValidationException;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {
  private static final Logger LOG = Logger.getLogger(PedidoResource.class);
  @Inject
  public PagamentoRepository pagamentoRepository;
  @Inject
  public PedidoRepository pedidoRepository;
  @Inject
  public ItemPedidoRepository itemPedidoRepository;
  @Inject
  public UsuarioRepository usuarioRepository;
  @Inject
  public UsuarioService usuarioService;
  @Inject
  public EnderecoService enderecoEntregaService;
  @Inject
  public LoteRepository loteRepository;
  @Inject
  public GuitarraRepository guitarraRepository;
  @Inject
  public LoteService loteService;
  @Inject
  public CidadeService cidadeService;
  @Inject
  public PedidoCanceladoRepository pedidoCanceladoRepository;
  @Inject
  EntityManager entityManager;

  @Override
  public Pedido findById(Long id) {
    Pedido pedido = pedidoRepository.findById(id);
    if (pedido != null) {
      return pedidoRepository.findById(id);
    }
    throw new EntidadeNotFoundException("id", "não foi encontrado o pedido");
  }

  @Override
  public List<Pedido> findByUsername(String username) {
    return pedidoRepository.findByUsername(username);
  }

  public Endereco createEnderecoFromDTO(PedidoDTORequest dto) {
    Endereco end = new Endereco();
    end.setBairro(dto.endereco().bairro());
    end.setCep(dto.endereco().cep());
    end.setCidade(cidadeService.findById(dto.endereco().idCidade()));
    end.setLogradouro(dto.endereco().logradouro());

    return end;
  }

  public List<ItemPedido> getItensFromDTO(PedidoDTORequest dto) {
    List<ItemPedido> item = new ArrayList<ItemPedido>();

    for (ItemPedidoDTORequest itemDTO : dto.listaItemPedido()) {

      ItemPedido itemUnidade = new ItemPedido();

      itemUnidade.setLote(loteRepository.findById(itemDTO.idLote()));
      itemUnidade.setQuantidade(itemDTO.quantidade());
      itemUnidade.setPreco(itemDTO.preco());

      item.add(itemUnidade);

    }
    return item;
  }

  public Double calculaValorTotal(PedidoDTORequest dto) {
    Pedido pedido = new Pedido();
    // todos os itens estão nessa variável
    pedido.setValorTotal(0d); // valor vai ser calculado

    List<ItemPedidoDTORequest> valorItens = dto.listaItemPedido();
    valorItens.forEach(t -> {
      pedido.setValorTotal(pedido.getValorTotal() + (t.quantidade() * t.preco()));
    });

    return pedido.getValorTotal();
  }

  @Override
  @Transactional
  public Pedido create(PedidoDTORequest dto) {
    // criando endereço
    Endereco end = createEnderecoFromDTO(dto);
    // Criando pedido
    Pedido pedido = new Pedido();
    pedido.setDataCompra(LocalDateTime.now());

    Usuario usuario = usuarioRepository.findById(dto.idUsuario());
    if (usuario == null)
      throw new EntidadeNotFoundException("id", "usuario não encontrado");

    pedido.setUsuario(usuario);

    pedido.setValorTotal(calculaValorTotal(dto));

    pedido.setEndereco(end);
    // prazo para efetuar o pagamento
    pedido.setTempoPagamento(LocalDateTime.now().plusMinutes(1)); // mudar para 5

    pedido.setListaItemPedido(getItensFromDTO(dto));

    if (pedido.getPagamento() == null) {
      LOG.warn("Seu pedido ainda não tem um pagamento.Para ser confirmado, selecione a forma de pagamento !");
    }
    StatusPedido statusPedido = new StatusPedido();
    statusPedido.setStatus(Status.AGUARDANDO_PAGAMENTO);

    pedidoRepository.persist(pedido);

    if (LocalDateTime.now().isAfter(pedido.getTempoPagamento())) {
      pedidoRepository.delete(pedido);
      LOG.warn("Pedido cancelado por conta do tempo de pagamento ultrapassado");
      throw new WebApplicationException("Pedido cancelado devido ao tempo de pagamento expirado");
    }

    List<StatusPedido> listaStatus = Arrays.asList(createStatusPedido(1));
    if (listaStatus.isEmpty()) {
      throw new IllegalArgumentException("Status inicial do pedido não pode ser vazio.");
    }
    pedido.setListaStatus(listaStatus);

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
    boleto.setDataFabricacao(LocalDate.now());
    boleto.setDataValidade(LocalDate.now().plusDays(7)); // validade de 7 dias

    pagamentoRepository.persist(boleto);

    LOG.info("informações do boleto geradas.");
    return BoletoDTOResponse.valueOf(boleto);

  }

  @Override
  @Transactional
  public void pagamentoBoleto(Long idPedido, Long idBoleto) {

    if (idPedido == null || idBoleto == null) {
      throw new IllegalArgumentException("Os IDs do pedido e do boleto devem ser fornecidos.");
    }

    
    Pedido pedido = pedidoRepository.findById(idPedido);
    if (pedido == null) {
      throw new EntidadeNotFoundException("id", "Não foi encontrado o pedido para o ID fornecido: " + idPedido);
    }

   
    Boleto boleto = (Boleto) pagamentoRepository.findById(idBoleto);
    if (boleto == null) {
      throw new EntidadeNotFoundException("id", "Não foi encontrado o boleto para o ID fornecido: " + idBoleto);
    }

    
    try {
      Thread.sleep(10000); // simula 10 segundos de processamento
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Erro durante o processamento do pagamento.", e);
    }

   
    boleto.setEstaPago(true);
    boleto.setDataPagamento(LocalDateTime.now());
    pedido.setPagamento(boleto);

   
    pagamentoRepository.persist(boleto);
    pedidoRepository.persist(pedido);

    LOG.info("Pagamento do boleto ID " + idBoleto + " para o pedido ID " + idPedido + " concluído com sucesso.");

    updateStatusPedido(pedido.getId(), 3);

  }

  @Override
  @Transactional
  public void pagamentoCartao(Long idPedido, CartaoCreditoDTORequest cartaoDTO) {
    Pedido p = pedidoRepository.findById(idPedido);

    if (p == null)
      throw new EntidadeNotFoundException("id", "Não foi encontrado o pedido");

    CartaoCredito c = CartaoCreditoDTORequest.converteCartaoCredito(cartaoDTO);

    // logica para descontar o saldo
    c.setValor(p.getValorTotal());

    if (c.getSaldoCartao() >= p.getValorTotal()) {
      c.setSaldoCartao(c.getSaldoCartao() - p.getValorTotal());
      try {
        Thread.sleep(10000); // simula 10 segundos de processamento
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException("Erro durante o processamento do pagamento.", e);
      }
      c.setEstaPago(true);
      LOG.info("Pagamento realizado com sucesso!");
      LocalDateTime now = LocalDateTime.now();
      c.setDataPagamento(now);
      // verificando se o tempo não foi ultrapassado
      if (now.isAfter(p.getTempoPagamento())) {
        cancelarPedidosExpirados();
        throw new IllegalArgumentException("Tempo esgotado");

      }
      p.setPagamento(c);
      updateStatusPedido(p.getId(), 3);

    } else {
      c.setEstaPago(false);
      throw new ValidationException("saldo", "Saldo insuficiente no cartão");
    }

    pagamentoRepository.persist(c);
    p.setPagamento(c);
  }

  @Override
  @Scheduled(every = "1m")
  @Transactional
  public void cancelarPedidosExpirados() {
    List<Pedido> pedidos = pedidoRepository.find("pagamento is NULL").list();

    pedidos.forEach(pedido -> {
      if (LocalDateTime.now().isAfter(pedido.getTempoPagamento())) {
        LOG.warn("Pedido ID: " + pedido.getId() + " foi cancelado porque o prazo de pagamento expirou.");
        updateStatusPedido(pedido.getId(), 2);
        LOG.info("Pedido com o pagamento expirado.");
        updateStatusPedido(pedido.getId(), 6);

        // persistindo os pedidos na table cancelados
        PedidoCancelado pedidoCancelado = new PedidoCancelado();
        pedidoCancelado.setDataCancelamento(LocalDateTime.now());
        pedidoCancelado.setPedidoCancelado(pedido);

        pedidoCanceladoRepository.persist(pedidoCancelado);

      }
    });
  }

  @Override
  @Transactional
  public void devolucao(Pedido pedido) {
    for (ItemPedido item : pedido.getListaItemPedido()) {
      Lote lote = item.getLote();
      Integer estoque = lote.getEstoque();
      lote.setEstoque(estoque + item.getQuantidade());
    }
    updateStatusPedido(pedido.getId(), 7); // atualizando o status para devolvido
  }

  @Override
  public List<PedidoDTOResponse> findByStatus(Integer idStatus) {
    return pedidoRepository.findByStatus(idStatus).stream().map(PedidoDTOResponse::valueOf).toList();
  }

  @Override
  public void delete(Long id) {
    pedidoRepository.deleteById(id);
  }

  @Override
  @Transactional
  public void updateStatusPedido(Long idPedido, Integer idStatus) {
    Pedido pedido = pedidoRepository.findById(idPedido);
    if (pedido == null) {
      throw new ValidationException("idPedido", "Pedido não encontrado");
    }
    StatusPedido statusPedido = createStatusPedido(idStatus);
    if (statusPedido == null) {
      throw new ValidationException("idStatus", "Status inválido");
    }

    pedido.getListaStatus().add(statusPedido);

    if (idStatus == 6) {
      PedidoCancelado pedidoCancelado = new PedidoCancelado();
      pedidoCancelado.setDataCancelamento(LocalDateTime.now());
      pedidoCancelado.setPedidoCancelado(pedido);

      pedidoCanceladoRepository.persist(pedidoCancelado);
    }
  }

  private StatusPedido createStatusPedido(Integer id) {
    StatusPedido statusPedido = new StatusPedido();

    statusPedido.setStatus(Status.valueOf(id));

    return statusPedido;
  }
}
