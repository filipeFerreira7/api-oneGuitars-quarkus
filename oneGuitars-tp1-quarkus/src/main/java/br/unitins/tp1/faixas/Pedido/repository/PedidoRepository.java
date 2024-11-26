package br.unitins.tp1.faixas.Pedido.repository;


import java.time.LocalDateTime;
import java.util.List;

import br.unitins.tp1.faixas.Pedido.ItemPedido.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

// Tudo que tem haver com bd


 public List<Pedido> findByIdUsuario(Long idUsuario) {    
       return find("SELECT p FROM Pedido p WHERE p.usuario = ?1","%"+idUsuario+"%").list();
    }

      public List<Pedido> findByUsername(String username){
        return find("SELECT p FROM Pedido p WHERE p.cliente.pessoaFisica.usuario.username = ?1", username).list();
}

  public List<Pedido> findPedidosExpirados(LocalDateTime data){
    return find ("WHERE ?1 > tempoPagamento AND pagamento is NULL",data).list();
  }

}


