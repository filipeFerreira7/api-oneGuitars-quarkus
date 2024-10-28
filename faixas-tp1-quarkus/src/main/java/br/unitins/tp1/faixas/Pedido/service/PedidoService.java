package br.unitins.tp1.faixas.Pedido.service;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.tp1.faixas.Pedido.dto.PedidoDTORequest;
import br.unitins.tp1.faixas.Pedido.model.Pedido;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;

@ApplicationScoped
public interface PedidoService {

    Pedido findById(Long id);

    List<Pedido> findByUserName(String username);
    
    List<Pedido> findByDataCompra(LocalDateTime dataCompra);

    Pedido create(@Valid PedidoDTORequest dto); 
    
    //implementar os patchs

    void delete(Long id);
}
