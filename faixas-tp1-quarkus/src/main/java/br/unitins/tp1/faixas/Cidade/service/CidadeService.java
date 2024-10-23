package br.unitins.tp1.faixas.Cidade.service;

import java.util.List;

import br.unitins.tp1.faixas.Cidade.dto.CidadeDTORequest;
import br.unitins.tp1.faixas.Cidade.model.Cidade;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface CidadeService {

    Cidade findById(Long id);

    List<Cidade> findByNome(String nome);
    
    List<Cidade> findAll();

    Cidade create(CidadeDTORequest dto); 
    
    Cidade update(Long id, CidadeDTORequest dto);

    void delete(Long id);
}
