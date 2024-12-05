package br.unitins.tp1.oneGuitars.Lote.service;
// 
import java.util.List;

import br.unitins.tp1.oneGuitars.Guitarra.service.GuitarraService;
import br.unitins.tp1.oneGuitars.Lote.dto.LoteDTORequest;
import br.unitins.tp1.oneGuitars.Lote.model.Lote;
import br.unitins.tp1.oneGuitars.Lote.repository.LoteRepository;
import br.unitins.tp1.oneGuitars.validation.EntidadeNotFoundException;
import br.unitins.tp1.oneGuitars.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class LoteServiceImpl implements LoteService{
  
  @Inject
  public LoteRepository loteRepository;

  @Inject
  public GuitarraService guitarraService;

  @Override
  public Lote findById(Long id) {
      return loteRepository.findById(id);
  }

  @Override
  public Lote findByIdGuitarra(Long idGuitarra) {
      return loteRepository.findByIdGuitarra(idGuitarra);
  }
  
  @Override
  public List<Lote> findAll() {
    return loteRepository.findAll().list();
  }

  @Override
  @Transactional
  public Lote create(LoteDTORequest dto) {
    Lote verificaExistente = loteRepository.findByCodigo(dto.codigo());
    if(verificaExistente !=null)
      throw new ValidationException("codigo", "Um lote com este codigo já existe");

    Lote lote = new Lote();
  
   lote.setCodigo(dto.codigo());
   lote.setData(dto.data());
   lote.setEstoque(dto.estoque());
   lote.setGuitarra(guitarraService.findById(dto.idGuitarra()));
   
     loteRepository.persist(lote);
     return lote;
  }

  @Override
  @Transactional
  public Lote update(Long id, LoteDTORequest dto) {
    Lote verificaExistente = loteRepository.findByCodigo(dto.codigo());
    if(verificaExistente != null && verificaExistente.getId() ==id){
      throw new ValidationException("id","Essa lote já existe.");
    }


    Lote lote = loteRepository.findById(id);
    if(lote == null)
        throw new IllegalArgumentException("Lote não encontrado");


    lote.setCodigo(dto.codigo());
    lote.setData(dto.data());
    lote.setEstoque(dto.estoque());
    lote.setGuitarra(guitarraService.findById(dto.idGuitarra()));
 
         loteRepository.persist(lote);
         return lote;
    }
  

  @Override
  @Transactional
  public void delete(Long id) {
    Lote lote = loteRepository.findById(id);

    if(lote==null)
          throw new IllegalArgumentException("Lote não encontrado");

          loteRepository.delete(lote);
  }

  @Override
  public Lote findByCodigo(String codigo){
      Lote lote = loteRepository.findByCodigo(codigo);

      if(lote ==null)
        throw new EntidadeNotFoundException("codigo","o lote não foi encontrado");
        

    return lote;
  }


  
}
