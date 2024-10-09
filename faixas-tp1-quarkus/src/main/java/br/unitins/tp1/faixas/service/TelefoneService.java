package br.unitins.tp1.faixas.service;

import java.util.List;

import br.unitins.tp1.faixas.DTO.TelefoneDTORequest;
import br.unitins.tp1.faixas.model.Telefone;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface TelefoneService {

    Telefone findById(Long id);

    List<Telefone> findByNumero(String numero);
    
    List<Telefone> findAll();

    Telefone create(TelefoneDTORequest dto); 
    
    Telefone update(Long id, TelefoneDTORequest dto);

    void delete(Long id);
}
