package br.unitins.tp1.faixas.Lote.service;

import java.util.List;

import br.unitins.tp1.faixas.Lote.dto.LoteDTORequest;
import br.unitins.tp1.faixas.Lote.model.Lote;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;

@ApplicationScoped
public interface LoteService {

    Lote findById(Long id);

    Lote findByCodigo(String codigo);

    List<Lote> findAll();

    Lote findByIdGuitarra(Long idGuitarra);
    
    Lote create(@Valid LoteDTORequest dto); 
    
    Lote update(Long id, LoteDTORequest dto);

    void delete(Long id);
}
