package br.unitins.tp1.faixas.Pedido.service;

import java.util.List;

import br.unitins.tp1.faixas.Pedido.dto.PedidoDTORequest;
import br.unitins.tp1.faixas.Pedido.model.Pedido;
import java.time.LocalDateTime;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;

@ApplicationScoped
public interface PedidoService {

    Pedido findById(Long id);

    List<Pedido> findAll();
    
    List<Pedido> findByDataCompra(LocalDateTime dataCompra);

    Pedido create(@Valid PedidoDTORequest dto); 
    
    Pedido update(Long id, PedidoDTORequest dto);

    void delete(Long id);
}
