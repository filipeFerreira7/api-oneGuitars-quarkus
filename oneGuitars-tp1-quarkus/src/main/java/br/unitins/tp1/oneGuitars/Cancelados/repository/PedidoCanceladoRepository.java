package br.unitins.tp1.oneGuitars.Cancelados.repository;

import java.util.List;

import br.unitins.tp1.oneGuitars.Cancelados.model.PedidoCancelado;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoCanceladoRepository implements PanacheRepository<PedidoCancelado> {
    
    public List<PedidoCancelado> findByIdUsuario(Long idUsuario){
        return find("SELECT p FROM PedidoCancelado p WHERE p.pedidoCancelado.usuario.id = ?1",idUsuario).list();
    }

    public PedidoCancelado findById(Long id){
        return find("SELECT p FROM PedidoCancelado p WHERE p.id = ?1",id).firstResult();
    }

}
