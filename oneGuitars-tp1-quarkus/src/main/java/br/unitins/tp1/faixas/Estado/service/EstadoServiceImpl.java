package br.unitins.tp1.faixas.Estado.service;
// 
import java.util.List;

import br.unitins.tp1.faixas.Estado.dto.EstadoDTORequest;
import br.unitins.tp1.faixas.Estado.model.Estado;
import br.unitins.tp1.faixas.Estado.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class EstadoServiceImpl implements EstadoService{

    @Inject
    public EstadoRepository estadoRepository;


    @Override
    public Estado findById(Long id) {
        return estadoRepository.findById(id);
    }

    @Override
    public List<Estado> findByNome(String nome) {
      return estadoRepository.findByNome(nome);
    }

    
    @Override
    public List<Estado> findAll() {
      return estadoRepository.findAll().list();
    }

    @Override
    @Transactional
    public Estado create(EstadoDTORequest dto) {
      Estado estado  = new Estado();
      estado.setNome(dto.nome());
      estado.setSigla(dto.sigla());
       estadoRepository.persist(estado);
       return estado;
    }

    @Override
    @Transactional
    public Estado update(long id, EstadoDTORequest dto) {
      Estado estado = estadoRepository.findById(id);
      estado.setNome(dto.nome());
      estado.setSigla(dto.sigla());

      estadoRepository.persist(estado);
      return estado;
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        estadoRepository.deleteById(id);
    }
    
}
