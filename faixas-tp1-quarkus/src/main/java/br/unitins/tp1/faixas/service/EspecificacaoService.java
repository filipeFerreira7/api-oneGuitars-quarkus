package br.unitins.tp1.faixas.service;

import java.util.List;

import br.unitins.tp1.faixas.DTO.EspecificacaoDTORequest;
import br.unitins.tp1.faixas.model.Especificacao;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface EspecificacaoService {

    Especificacao findById(Long id);

    List<Especificacao> findByNome(String nome);
    
    List<Especificacao> findAll();

    Especificacao create(EspecificacaoDTORequest dto); 
    
    Especificacao update(Long id, EspecificacaoDTORequest dto);

    void delete(Long id);
}
