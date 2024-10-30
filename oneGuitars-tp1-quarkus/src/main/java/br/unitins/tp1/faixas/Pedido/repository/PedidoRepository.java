package br.unitins.tp1.faixas.Pedido.repository;


import java.time.LocalDateTime;
import java.util.List;

import br.unitins.tp1.faixas.Pedido.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

// Tudo que tem haver com bd

public List<Pedido> findByDataCompra(LocalDateTime dataCompra) {
    return find("SELECT p FROM Pedido p WHERE p.dataCompra = ?1", dataCompra).list();
}

 public List<Pedido> findByUserName(String username) {    
       return find("SELECT p FROM Pedido p WHERE p.nome LIKE ?1","%"+username+"%").list();
    }
}


