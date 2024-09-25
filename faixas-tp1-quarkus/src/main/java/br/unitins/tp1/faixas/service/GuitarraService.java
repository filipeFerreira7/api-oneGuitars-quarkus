package br.unitins.tp1.faixas.service;

import java.util.List;

import br.unitins.tp1.faixas.DTO.GuitarraDTO;
import br.unitins.tp1.faixas.DTO.GuitarraDTOResponse;
import br.unitins.tp1.faixas.model.Guitarra;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface GuitarraService {

    Guitarra findById(Long id);

    List<Guitarra> findByNome(String nome);
    
    List<Guitarra> findAll();

    GuitarraDTOResponse create(GuitarraDTO guitarra); 
    
    Guitarra update(Guitarra guitarra);

    void delete(Long id);
}
