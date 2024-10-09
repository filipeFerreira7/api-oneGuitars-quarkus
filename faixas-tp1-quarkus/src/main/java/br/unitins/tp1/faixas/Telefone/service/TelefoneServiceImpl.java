package br.unitins.tp1.faixas.service;
// 
import java.util.List;

import br.unitins.tp1.faixas.DTO.TelefoneDTORequest;
import br.unitins.tp1.faixas.model.Telefone;
import br.unitins.tp1.faixas.repository.TelefoneRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class TelefoneServiceImpl implements TelefoneService{

    @Inject
    public TelefoneRepository telefoneRepository;


    @Override
    public Telefone findById(Long id) {
        return telefoneRepository.findById(id);
    }

    @Override
    public List<Telefone> findByNumero(String numero) {
      return telefoneRepository.findByNumero(numero);
    }

    
    @Override
    public List<Telefone> findAll() {
      return telefoneRepository.findAll().list();
    }

    @Override
    @Transactional
    public Telefone create(TelefoneDTORequest dto) {
      Telefone telefone  = new Telefone();
      telefone.setDd(dto.dd());
      telefone.setNumero(dto.numero());
       telefoneRepository.persist(telefone);
       return telefone;
    }

    @Override
    @Transactional
    public Telefone update(Long id, TelefoneDTORequest dto) {
      Telefone telefone = telefoneRepository.findById(id);
      telefone.setDd(dto.dd());
      telefone.setNumero(dto.numero());

      telefoneRepository.persist(telefone);
      return telefone;
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        telefoneRepository.deleteById(id);
    }
    
}
