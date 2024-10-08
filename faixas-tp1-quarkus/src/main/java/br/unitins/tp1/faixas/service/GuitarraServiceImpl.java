package br.unitins.tp1.faixas.service;
// 
import java.util.List;

import br.unitins.tp1.faixas.DTO.GuitarraDTORequest;
import br.unitins.tp1.faixas.model.Guitarra;
import br.unitins.tp1.faixas.repository.EspecificacaoRepository;
import br.unitins.tp1.faixas.repository.GuitarraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class GuitarraServiceImpl implements GuitarraService{

  
  @Inject
  public GuitarraRepository guitarraRepository;

    @Inject
    public EspecificacaoRepository repository;
  
    @Inject
    public EspecificacaoService especificacaoService;

  @Override
  public Guitarra findById(Long id) {
      return guitarraRepository.findById(id);
  }

  @Override
  public List<Guitarra> findByNome(String nome) {
    return guitarraRepository.findByNome(nome);
  }

  
  @Override
  public List<Guitarra> findAll() {
    return guitarraRepository.findAll().list();
  }

  @Override
  @Transactional
  public Guitarra create(GuitarraDTORequest dto) {
    Guitarra guitarra = new Guitarra();
    guitarra.setNome(dto.nome());
    guitarra.setNumeroSerie(dto.numeroSerie());
    guitarra.setEspecificacao(especificacaoService.findById(dto.idEspecificacao()));
     guitarraRepository.persist(guitarra);
     return guitarra;
  }

  @Override
  @Transactional
  public Guitarra update(Long id, GuitarraDTORequest dto) {
        Guitarra guitarra = guitarraRepository.findById(id);
        guitarra.setNome(dto.nome());
        guitarra.setNumeroSerie(dto.numeroSerie());
        guitarra.setEspecificacao(repository.findById(dto.idEspecificacao()));
        guitarraRepository.persist(guitarra);

        return guitarra;
    }
  

  @Override
  @Transactional
  public void delete(Long id) {
      guitarraRepository.deleteById(id);
  }
  
}
