package br.unitins.tp1.faixas.Especificacao.service;
// 
import java.util.List;

import br.unitins.tp1.faixas.Especificacao.dto.EspecificacaoDTORequest;
import br.unitins.tp1.faixas.Especificacao.model.Especificacao;
import br.unitins.tp1.faixas.Especificacao.repository.EspecificacaoRepository;
import br.unitins.tp1.faixas.Estado.repository.EstadoRepository;
import br.unitins.tp1.faixas.validation.EntidadeNotFoundException;
import br.unitins.tp1.faixas.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class EspecificacaoServiceImpl implements EspecificacaoService {

    @Inject
    public EspecificacaoRepository especificacaoRepository;

    @Inject
    public EstadoRepository repository;



    @Override
    public Especificacao findById(Long id) {
        Especificacao especificacao =  especificacaoRepository.findById(id);

        if(especificacao == null){
          throw new EntidadeNotFoundException("id", "não foi encontrado o produto pelo id");
        }

        return especificacao;
    }

    @Override
    public List<Especificacao> findBySku(String sku){
      return especificacaoRepository.findBySku(sku);
    }

    
    @Override
    public List<Especificacao> findAll() {
      return especificacaoRepository.findAll().list();
    }

    @Override
    @Transactional
    public Especificacao create(EspecificacaoDTORequest dto) {
      List<Especificacao> especificacoes = especificacaoRepository.findBySku(dto.sku());
      if (!especificacoes.isEmpty()) {
          throw new ValidationException("sku","Já existe uma especificação com o SKU informado: " + dto.sku());
      }

      Especificacao especificacao = new Especificacao();
      especificacao.setSku(dto.sku());
      especificacao.setComprimento(dto.comprimento());
      especificacao.setTipoMadeira(dto.tipoMadeira());
      especificacao.setTipoCaptador(dto.tipoCaptador());
      especificacao.setTipoChave(dto.tipoChave());

      especificacaoRepository.persist(especificacao);
      
       return especificacao;
    }

    @Override
    @Transactional
    public Especificacao update(Long id , EspecificacaoDTORequest dto) {
     
      Especificacao especificacao = especificacaoRepository.findById(id);
      especificacao.setSku(dto.sku());
      especificacao.setComprimento(dto.comprimento());
      especificacao.setTipoMadeira(dto.tipoMadeira());
      especificacao.setTipoCaptador(dto.tipoCaptador());
      especificacao.setTipoChave(dto.tipoChave());

      especificacaoRepository.persist(especificacao);
       return especificacao;
  }
  

    @Override
    @Transactional
    public void delete(Long id) {
        especificacaoRepository.deleteById(id);
        
    }
  }
    
