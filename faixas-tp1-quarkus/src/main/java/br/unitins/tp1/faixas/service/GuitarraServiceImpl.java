package br.unitins.tp1.faixas.service;
// 
import java.util.List;

import br.unitins.tp1.faixas.DTO.GuitarraDTO;
import br.unitins.tp1.faixas.DTO.GuitarraDTOResponse;
import br.unitins.tp1.faixas.model.Guitarra;
import br.unitins.tp1.faixas.repository.GuitarraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class GuitarraServiceImpl implements GuitarraService{

  
  @Inject
  public GuitarraRepository guitarraRepository;


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
  public GuitarraDTOResponse create(GuitarraDTO dto) {
    Guitarra guitarra = new Guitarra();
    guitarra.setNome(dto.nome());
    guitarra.setNumeroSerie(dto.numeroSerie());

     guitarraRepository.persist(guitarra);
     return GuitarraDTOResponse.valueOf(guitarra);
  }

  @Override
  public Guitarra update(Guitarra guitarra) {
      Guitarra e = guitarraRepository.findById(guitarra.getId());
      e.setNome(guitarra.getNome());
      e.setNumeroSerie(guitarra.getNumeroSerie());
      return guitarra;
  }

  @Override
  @Transactional
  public void delete(Long id) {
      guitarraRepository.deleteById(id);
  }
}
