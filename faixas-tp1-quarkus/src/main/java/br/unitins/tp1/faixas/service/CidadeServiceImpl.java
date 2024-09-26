package br.unitins.tp1.faixas.service;
// 
import java.util.List;

import br.unitins.tp1.faixas.model.Cidade;
import br.unitins.tp1.faixas.repository.CidadeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class CidadeServiceImpl implements CidadeService {

    @Inject
    public CidadeRepository cidadeRepository;
  
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
    public Cidade create(Cidade cidade) {
      cidade.setEstado(estadoService.findById(cidade.getEstado().getId()));
       cidadeRepository.persist(cidade);
       return cidade;
    }

    @Override
    public Cidade update(Cidade cidade) {
       Cidade c = cidadeRepository.findById(cidade.getId());
        c.setNome(cidade.getNome());

        //buscando o estado a partir de um id do municipio
        c.setEstado(estadoService.findById(cidade.getEstado().getId()));
        return cidade;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cidadeRepository.deleteById(id);
    }
    
}
