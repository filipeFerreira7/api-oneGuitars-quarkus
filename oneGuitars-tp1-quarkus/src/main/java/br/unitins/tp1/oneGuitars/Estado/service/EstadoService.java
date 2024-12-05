package br.unitins.tp1.oneGuitars.Estado.service;

import java.util.List;

import br.unitins.tp1.oneGuitars.Estado.dto.EstadoDTORequest;
import br.unitins.tp1.oneGuitars.Estado.model.Estado;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface EstadoService {

    Estado findById(Long id);

    List<Estado> findByNome(String nome);
    
    List<Estado> findAll();

    Estado create(EstadoDTORequest dto); 
    
    Estado update(long id, EstadoDTORequest dto);

    void delete(Long id);
}
