package br.unitins.tp1.faixas.Pedido.service;

import java.util.List;

import br.unitins.tp1.faixas.Pedido.dto.PedidoDTORequest;
import br.unitins.tp1.faixas.Pedido.model.Pedido;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;

@ApplicationScoped
public interface PedidoService {

    Pedido findById(Long id);

    
    List<Pedido> findByUsername(String username);

    Pedido create(@Valid PedidoDTORequest dto); 
    
    //implementar os patchs

   //pensar no cancelar
}
