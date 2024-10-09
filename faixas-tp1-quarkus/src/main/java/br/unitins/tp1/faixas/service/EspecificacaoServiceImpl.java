package br.unitins.tp1.faixas.service;
// 
import java.util.List;

import br.unitins.tp1.faixas.DTO.EspecificacaoDTORequest;
import br.unitins.tp1.faixas.model.Especificacao;
import br.unitins.tp1.faixas.repository.EspecificacaoRepository;
import br.unitins.tp1.faixas.repository.EstadoRepository;
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
        return especificacaoRepository.findById(id);
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
    
