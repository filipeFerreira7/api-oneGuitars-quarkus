package br.unitins.tp1.oneGuitars.Cancelados.service;

import java.util.List;

import br.unitins.tp1.oneGuitars.Cancelados.model.PedidoCancelado;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface PedidoCanceladoService {
    
    PedidoCancelado findById(Long id);

   List<PedidoCancelado> findByIdUsuario(Long idUsuario);

    List<PedidoCancelado> findAll();

    
}
