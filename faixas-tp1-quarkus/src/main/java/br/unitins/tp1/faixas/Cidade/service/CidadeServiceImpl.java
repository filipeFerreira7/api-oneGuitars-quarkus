package br.unitins.tp1.faixas.Cidade.service;
// 
import java.util.List;

import br.unitins.tp1.faixas.Cidade.dto.CidadeRequestDTO;
import br.unitins.tp1.faixas.Cidade.model.Cidade;
import br.unitins.tp1.faixas.Cidade.repository.CidadeRepository;
import br.unitins.tp1.faixas.Estado.repository.EstadoRepository;
import br.unitins.tp1.faixas.Estado.service.EstadoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class CidadeServiceImpl implements CidadeService {

    @Inject
    public CidadeRepository cidadeRepository;

    @Inject
    public EstadoRepository repository;
  
    @Inject
    public EstadoService estadoService;


    @Override
    public Cidade findById(Long id) {
        return cidadeRepository.findById(id);
    }

    @Override
    public List<Cidade> findByNome(String nome) {
      return cidadeRepository.findByNome(nome);
    }

    
    @Override
    public List<Cidade> findAll() {
      return cidadeRepository.findAll().list();
    }

    @Override
    @Transactional
    public Cidade create(CidadeRequestDTO dto) {
      Cidade cidade = new Cidade();
      cidade.setEstado(estadoService.findById(dto.idEstado()));
      cidade.setNome(dto.nome());

      cidadeRepository.persist(cidade);
       return cidade;
    }

    @Override
    @Transactional
    public Cidade update(Long id , CidadeRequestDTO dto) {
      Cidade m = cidadeRepository.findById(id);
      m.setNome(dto.nome());
      // buscando o estado a partir de um id do municipio
      m.setEstado(repository.findById(dto.idEstado()));

      cidadeRepository.persist(m);

      return m; 
  }
  

    @Override
    @Transactional
    public void delete(Long id) {
        cidadeRepository.deleteById(id);
        
    }
  }
    
