package br.unitins.tp1.faixas.Telefone.service;
// 
import java.util.List;

import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTORequest;
import br.unitins.tp1.faixas.Telefone.model.Telefone;
import br.unitins.tp1.faixas.Telefone.repository.TelefoneRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;


@ApplicationScoped
public class TelefoneServiceImpl implements TelefoneService{

    @Inject
    public TelefoneRepository telefoneRepository;


    @Override
    public Telefone findById(Long id) throws NotFoundException {
      Telefone t = telefoneRepository.findById(id);

      if(t==null)
      throw new NotFoundException("Telefone não foi encontrado");

        return t;
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
    public Telefone update(Long id, TelefoneDTORequest dto) throws NotFoundException {
      Telefone telefone = telefoneRepository.findById(id);
      if(telefone == null)
      throw new NotFoundException("telefone não pode ser atualizado. Id não encontrado");

      telefone.setDd(dto.dd());
      telefone.setNumero(dto.numero());

      telefoneRepository.persist(telefone);
      return telefone;
    }
    
    @Override
    @Transactional
    public void delete(Long id) throws NotFoundException {
      Telefone t = telefoneRepository.findById(id);

      if(t==null)
      throw new NotFoundException("telefone não encontrado");

        telefoneRepository.deleteById(id);
    }
    
}
