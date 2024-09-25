package br.unitins.tp1.faixas.service;

import java.util.List;

import br.unitins.tp1.faixas.model.Estado;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface EstadoService {

    Estado findById(Long id);

    List<Estado> findByNome(String nome);
    
    List<Estado> findAll();

    Estado create(Estado estado); 
    
    Estado update(Estado estado);

    void delete(Long id);
}
