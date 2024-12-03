package br.unitins.tp1.faixas.Cidade.service;
// 
import java.util.List;

import br.unitins.tp1.faixas.Cidade.dto.CidadeDTORequest;
import br.unitins.tp1.faixas.Cidade.model.Cidade;
import br.unitins.tp1.faixas.Cidade.repository.CidadeRepository;
import br.unitins.tp1.faixas.Estado.repository.EstadoRepository;
import br.unitins.tp1.faixas.Estado.service.EstadoService;
import br.unitins.tp1.faixas.validation.EntidadeNotFoundException;
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
        Cidade cidade = cidadeRepository.findById(id);
        if(cidade ==null)
          throw new EntidadeNotFoundException("id","Cidade não encontrada");
        
        return cidade;
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
    public Cidade create(CidadeDTORequest dto) {
      Cidade cidade = new Cidade();
      if(estadoService.findById(dto.idEstado()) == null){
        throw new EntidadeNotFoundException("idEstado","Estado não entcontrado");
      }
      cidade.setEstado(estadoService.findById(dto.idEstado()));
      cidade.setNome(dto.nome());

      cidadeRepository.persist(cidade);
       return cidade;
    }

    @Override
    @Transactional
    public Cidade update(Long id , CidadeDTORequest dto) {
      Cidade c = cidadeRepository.findById(id);
      if(c ==null)
          throw new EntidadeNotFoundException("id","Cidade não encontrada");
      c.setNome(dto.nome());
      if(estadoService.findById(dto.idEstado()) == null){
        throw new EntidadeNotFoundException("idEstado","Estado não encontrado");
      }
      c.setEstado(repository.findById(dto.idEstado()));

      cidadeRepository.persist(c);

      return c; 
  }
    @Override
    @Transactional
    public void delete(Long id) {
        cidadeRepository.deleteById(id);
        
    }
  }
    
