package br.unitins.tp1.faixas.Marca.service;

import java.util.List;

import br.unitins.tp1.faixas.Marca.dto.MarcaDTORequest;
import br.unitins.tp1.faixas.Marca.model.Marca;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;

@ApplicationScoped
public interface MarcaService {

    Marca findById(Long id);

    Marca findByNome(String nome);

    List<Marca> findAll();

    Marca create(@Valid MarcaDTORequest dto); 
    
    Marca update(Long id, MarcaDTORequest dto);

    void delete(Long id);
}
