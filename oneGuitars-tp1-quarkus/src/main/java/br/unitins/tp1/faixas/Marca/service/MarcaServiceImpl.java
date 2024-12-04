package br.unitins.tp1.faixas.Marca.service;
// 
import java.util.List;

import br.unitins.tp1.faixas.Especificacao.repository.EspecificacaoRepository;
import br.unitins.tp1.faixas.Especificacao.service.EspecificacaoService;
import br.unitins.tp1.faixas.Marca.dto.MarcaDTORequest;
import br.unitins.tp1.faixas.Marca.model.Marca;
import br.unitins.tp1.faixas.Marca.repository.MarcaRepository;
import br.unitins.tp1.faixas.validation.EntidadeNotFoundException;
import br.unitins.tp1.faixas.validation.ValidationException;
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
      Marca marca = marcaRepository.findById(id);
      if(marca ==null)
        throw new EntidadeNotFoundException("id", "não foi possivel achar a marca");
        
    return marca;
  }


  @Override
  public Marca findByNome(String nome) {
    Marca marca =  marcaRepository.findByNome(nome);

    if(marca == null)
      throw new EntidadeNotFoundException("nome", "não foi possível encontrar a marca");
    return marca;
   }


  
  @Override
  public List<Marca> findAll() {
    return marcaRepository.findAll().list();
  }

  @Override
  @Transactional
  public Marca create(MarcaDTORequest dto) {
    Marca marcaNome = marcaRepository.findByNome(dto.nome());
    if(marcaNome !=null)
      throw new ValidationException("nome", "Uma marca com este nome já existe");
    Marca marca = new Marca();
    marca.setNome(dto.nome());
     marcaRepository.persist(marca);
     return marca;
  }

  @Override
  @Transactional
  public Marca update(Long id, MarcaDTORequest dto) {
        Marca marcaNome = marcaRepository.findByNome(dto.nome());
        if(marcaNome!=null && marcaNome.getId() != id){
          throw new ValidationException("id","Essa marca já existe.");
        }

      Marca marca = marcaRepository.findById(id);
      if(marca==null)
        throw new EntidadeNotFoundException("id", "marca não encontrada");
      
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
