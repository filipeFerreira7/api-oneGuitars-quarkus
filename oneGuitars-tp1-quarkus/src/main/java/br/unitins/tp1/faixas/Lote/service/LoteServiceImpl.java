package br.unitins.tp1.faixas.Lote.service;
// 
import java.util.List;

import br.unitins.tp1.faixas.Guitarra.service.GuitarraService;
import br.unitins.tp1.faixas.Lote.dto.LoteDTORequest;
import br.unitins.tp1.faixas.Lote.model.Lote;
import br.unitins.tp1.faixas.Lote.repository.LoteRepository;
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
  public List<Lote> findAll() {
    return loteRepository.findAll().list();
  }

  @Override
  @Transactional
  public Lote create(LoteDTORequest dto) {
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
  public List<Lote> findByCodigo(String codigo){
      return loteRepository.findByCodigo(codigo);
  }


  
}
