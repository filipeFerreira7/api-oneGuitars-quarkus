package br.unitins.tp1.faixas.Marca.service;
// 
import java.util.List;

import br.unitins.tp1.faixas.Especificacao.repository.EspecificacaoRepository;
import br.unitins.tp1.faixas.Especificacao.service.EspecificacaoService;
import br.unitins.tp1.faixas.Marca.dto.MarcaDTORequest;
import br.unitins.tp1.faixas.Marca.model.Marca;
import br.unitins.tp1.faixas.Marca.repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class MarcaServiceImpl implements MarcaService{

  
  @Inject
  public MarcaRepository marcaRepository;

    @Inject
    public EspecificacaoRepository repository;
  
    @Inject
    public EspecificacaoService especificacaoService;

  @Override
  public Marca findById(Long id) {
      return marcaRepository.findById(id);
  }


  @Override
  public List<Marca> findByNome(String nome) {
    return marcaRepository.findByNome(nome);
  }

  
  @Override
  public List<Marca> findAll() {
    return marcaRepository.findAll().list();
  }

  @Override
  @Transactional
  public Marca create(MarcaDTORequest dto) {
    Marca marca = new Marca();
    marca.setNome(dto.nome());
     marcaRepository.persist(marca);
     return marca;
  }

  @Override
  @Transactional
  public Marca update(Long id, MarcaDTORequest dto) {
        Marca marca = marcaRepository.findById(id);
        marca.setNome(dto.nome());
         marcaRepository.persist(marca);
         return marca;
    }
  

  @Override
  @Transactional
  public void delete(Long id) {
      marcaRepository.deleteById(id);
  }
  
}
