package br.unitins.tp1.faixas.Cancelados.service;

import java.util.List;

import br.unitins.tp1.faixas.Cancelados.model.PedidoCancelado;
import br.unitins.tp1.faixas.Cancelados.repository.PedidoCanceladoRepository;
import br.unitins.tp1.faixas.Pedido.model.Pedido;
import br.unitins.tp1.faixas.Pedido.repository.PedidoRepository;
import br.unitins.tp1.faixas.Usuario.repository.UsuarioRepository;
import br.unitins.tp1.faixas.validation.EntidadeNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoCanceladoServiceImpl implements PedidoCanceladoService{
     @Inject
    PedidoCanceladoRepository pedidoCanceladoRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    EntityManager entityManager;

    @Override
    public PedidoCancelado findById(Long id) {
        PedidoCancelado p = pedidoCanceladoRepository.findById(id);

        if(p==null)
            throw new EntidadeNotFoundException("id","não existe pedido cancelado com este id");
    
    return p;    
    }

    @Override
    public List<PedidoCancelado> findByIdUsuario(Long idUsuario){
        List<PedidoCancelado> pedidos = pedidoCanceladoRepository.findByIdUsuario(idUsuario);
        
        if(pedidos ==null || pedidos.isEmpty())
            throw new EntidadeNotFoundException("idUsuario", "não existe pedidos cancelados com este id");
        
        return pedidos;
    }

    @Override
    public List<PedidoCancelado> findAll() {
        return pedidoCanceladoRepository.listAll();
    }

      @Override
    @Transactional
    public void cancelarPedido(Long idPedido) {
        // busca o pedido
        Pedido pedido = pedidoRepository.findById(idPedido);
        
        if (pedido == null) {
            throw new EntidadeNotFoundException("id", "Pedido não encontrado para o ID fornecido: " + idPedido);
        }

        Pedido pedidoGerenciado = entityManager.merge(pedido);

        // cria o pedido cancelado e transfere os dados
        PedidoCancelado pedidoCancelado = new PedidoCancelado();
        pedidoCancelado.setId(pedido.getId());
        pedidoCancelado.setDataCancelamento(java.time.LocalDateTime.now());
        pedidoCancelado.setPedidoCancelado(pedidoGerenciado);
        
        // Persistir o pedido cancelado
        entityManager.merge(pedidoCancelado);

        pedidoRepository.delete(pedido);

        
    }
}
