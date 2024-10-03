package br.unitins.tp1.faixas.service;
// 
import java.util.List;

import br.unitins.tp1.faixas.DTO.CidadeRequestDTO;
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
    public Cidade create(CidadeRequestDTO dto) {
      Cidade cidade = new Cidade();
      cidade.setEstado(estadoService.findById(dto.idEstado()));
      cidade.setNome(dto.nome());

      cidadeRepository.persist(cidade);
       return cidade;
    }

    @Override
    public Cidade update(Long id, CidadeRequestDTO dto) {
       Cidade cidade = cidadeRepository.findById(id);
        cidade.setNome(dto.nome());

        //buscando o estado a partir de um id do municipio
        cidade.setEstado(estadoService.findById(dto.idEstado()));
        return cidade;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cidadeRepository.deleteById(id);
    }
    
}
