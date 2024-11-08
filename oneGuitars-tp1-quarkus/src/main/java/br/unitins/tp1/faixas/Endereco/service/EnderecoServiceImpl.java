package br.unitins.tp1.faixas.Endereco.service;

import java.util.List;

import br.unitins.tp1.faixas.Cidade.repository.CidadeRepository;
import br.unitins.tp1.faixas.Cidade.service.CidadeService;
import br.unitins.tp1.faixas.Endereco.dto.EnderecoDTORequest;
import br.unitins.tp1.faixas.Endereco.dto.EnderecoDTOResponse;
import br.unitins.tp1.faixas.Endereco.model.Endereco;
import br.unitins.tp1.faixas.Endereco.repository.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {
    

    @Inject
    EnderecoRepository repository;

    @Inject
    CidadeRepository cidadeRepository;

    @Inject
    CidadeService cidadeService;

    @Override
    public Endereco findyById(Long id) throws NotFoundException{
        Endereco e = repository.findById(id);

        if(e==null)
            throw new NotFoundException("Endereço não encontrado!");

        return repository.findById(id);
    }

    @Override
    public Endereco findByCep(String cep) throws NotFoundException
     {
         Endereco e = repository.findByCep(cep);

         if(e==null)
         throw new NotFoundException("CEP não pode ser encontrado");

         return repository.findByCep(cep);
  }
        
    @Transactional
    @Override
    public List<EnderecoDTOResponse> findAll() {
        return repository.findAll().stream().map(e -> EnderecoDTOResponse.valueOf(e)).toList();
    }

    @Override
    @Transactional
    public EnderecoDTOResponse create(EnderecoDTORequest dto) {
       Endereco endereco = new Endereco();
       endereco.setBairro(dto.bairro());
       endereco.setCep(dto.cep());
       endereco.setLogradouro(dto.logradouro());
       endereco.setCidade(cidadeService.findById(dto.idCidade()));

       repository.persist(endereco);

       return EnderecoDTOResponse.valueOf(endereco);
    }
       
  

    @Override
    @Transactional
    public EnderecoDTOResponse update(Long id, EnderecoDTORequest dto) throws NotFoundException {
      Endereco e = repository.findById(id);

      if(e==null)
        throw new NotFoundException("Endereço não encontrado!");

        Endereco endereco = new Endereco();
       endereco.setBairro(dto.bairro());
       endereco.setCep(dto.cep());
       endereco.setLogradouro(dto.logradouro());
       endereco.setCidade(cidadeService.findById(dto.idCidade()));

       repository.persist(endereco);

       return EnderecoDTOResponse.valueOf(endereco);
    }

    @Override
    @Transactional
    public void delete(Long id) {
         repository.deleteById(id);
    }
}

