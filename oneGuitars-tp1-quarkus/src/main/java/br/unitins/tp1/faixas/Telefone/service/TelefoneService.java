package br.unitins.tp1.faixas.Telefone.service;

import java.util.List;

import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTORequest;
import br.unitins.tp1.faixas.Telefone.model.Telefone;
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
