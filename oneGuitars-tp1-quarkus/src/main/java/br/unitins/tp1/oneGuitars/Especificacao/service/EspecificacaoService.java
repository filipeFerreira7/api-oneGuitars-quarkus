package br.unitins.tp1.oneGuitars.Especificacao.service;

import java.util.List;

import br.unitins.tp1.oneGuitars.Especificacao.dto.EspecificacaoDTORequest;
import br.unitins.tp1.oneGuitars.Especificacao.model.Especificacao;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface EspecificacaoService {

    Especificacao findById(Long id);

    List<Especificacao> findBySku(String sku);
    
    List<Especificacao> findAll();

    Especificacao create(EspecificacaoDTORequest dto); 
    
    Especificacao update(Long id, EspecificacaoDTORequest dto);

    void delete(Long id);
}
