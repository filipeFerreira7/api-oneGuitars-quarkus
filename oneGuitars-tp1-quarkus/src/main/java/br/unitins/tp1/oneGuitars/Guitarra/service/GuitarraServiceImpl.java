package br.unitins.tp1.oneGuitars.Guitarra.service;
// 
import java.util.List;

import br.unitins.tp1.oneGuitars.Especificacao.repository.EspecificacaoRepository;
import br.unitins.tp1.oneGuitars.Especificacao.service.EspecificacaoService;
import br.unitins.tp1.oneGuitars.Guitarra.dto.GuitarraDTORequest;
import br.unitins.tp1.oneGuitars.Guitarra.model.Guitarra;
import br.unitins.tp1.oneGuitars.Guitarra.repository.GuitarraRepository;
import br.unitins.tp1.oneGuitars.Marca.service.MarcaService;
import br.unitins.tp1.oneGuitars.validation.EntidadeNotFoundException;
import br.unitins.tp1.oneGuitars.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Validator;


@ApplicationScoped
public class GuitarraServiceImpl implements GuitarraService{

  
  @Inject
  public GuitarraRepository guitarraRepository;

    @Inject
    public EspecificacaoRepository repository;
  
    @Inject
    public EspecificacaoService especificacaoService;
    
    @Inject
    public MarcaService marcaService;

    
    @Inject 
    Validator validator;

  @Override
  public Guitarra findById(Long id) {
      Guitarra guitarra =  guitarraRepository.findById(id);

      if(guitarra == null)
        throw new EntidadeNotFoundException("id", "não foi possivel encontrar a guitarra");
 
    return guitarra;
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
   Guitarra guitarraNumeroSerie = guitarraRepository.findByNumeroSerie(dto.numeroSerie());
      if (guitarraNumeroSerie != null) {
          throw new ValidationException("numeroSerie","Já existe uma guitara com esse Numero de Série: " + dto.numeroSerie());
      }

    Guitarra guitarra = new Guitarra();
    guitarra.setNome(dto.nome());
    guitarra.setNumeroSerie(dto.numeroSerie());
    guitarra.setCor(dto.cor());
    guitarra.setPreco(dto.preco());
    guitarra.setEspecificacao(especificacaoService.findById(dto.idEspecificacao()));
    guitarra.setMarca(marcaService.findById(dto.idMarca()));
     guitarraRepository.persist(guitarra);
     return guitarra;
  }

  @Override
  @Transactional
  public Guitarra update(Long id, GuitarraDTORequest dto) {
    Guitarra guitarraNumeroSerie = guitarraRepository.findByNumeroSerie(dto.numeroSerie());
    if (guitarraNumeroSerie != null) {
      throw new ValidationException("numeroSerie","Já existe uma guitara com esse Numero de Série: " + dto.numeroSerie());
     }

        Guitarra guitarra = guitarraRepository.findById(id);
        guitarra.setNome(dto.nome());
        guitarra.setNumeroSerie(dto.numeroSerie());
        guitarra.setCor(dto.cor());
        guitarra.setPreco(dto.preco());
        guitarra.setEspecificacao(repository.findById(dto.idEspecificacao()));
        guitarra.setMarca(marcaService.findById(dto.idMarca()));
        guitarraRepository.persist(guitarra);

        return guitarra;
    }

    @Override
    @Transactional
    public Guitarra updatePreco(Long idGuitarra, Double novoPreco){
      if(novoPreco == null || novoPreco <= 0)
        throw new ValidationException("preco", "O preço deve ser maior que zero");
      
      Guitarra guitarra = guitarraRepository.findById(idGuitarra);
      if(guitarra == null)
        throw new EntidadeNotFoundException("idGuitarra", "não foi possível encontrar a guitarra");
      guitarra.setPreco(novoPreco);
      guitarraRepository.persist(guitarra);

      return guitarra;
      }
  

  @Override
  @Transactional
  public void delete(Long id) {
      guitarraRepository.deleteById(id);
  }
  
}
