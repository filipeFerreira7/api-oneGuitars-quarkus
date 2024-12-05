package br.unitins.tp1.oneGuitars.Endereco.service;

import java.util.List;

import br.unitins.tp1.oneGuitars.Cidade.repository.CidadeRepository;
import br.unitins.tp1.oneGuitars.Cidade.service.CidadeService;
import br.unitins.tp1.oneGuitars.Endereco.dto.EnderecoDTORequest;
import br.unitins.tp1.oneGuitars.Endereco.dto.EnderecoDTOResponse;
import br.unitins.tp1.oneGuitars.Endereco.model.Endereco;
import br.unitins.tp1.oneGuitars.Endereco.repository.EnderecoRepository;
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
    public Endereco findById(Long id) throws NotFoundException{
        Endereco e = repository.findById(id);
        
        if(e == null)
            throw new NotFoundException("Endereco Entrega não encontrado");
        return repository.findById(id);
    }

    @Override
    public Endereco findByCep(String cep) throws NotFoundException{
        Endereco e = repository.findByCep(cep);

        if(e==null)
        throw new NotFoundException("Cep do endereco não encontrado");
    
        return repository.findByCep(cep);
    }

    @Transactional
    @Override
    public List<EnderecoDTOResponse> findAll(){
        return repository.findAll().stream().map(e -> EnderecoDTOResponse.valueOf(e)).toList();
    }

    @Override
    @Transactional
    public EnderecoDTOResponse create(EnderecoDTORequest dto){
        Endereco eE = new Endereco();
        eE.setLogradouro(dto.logradouro());
        eE.setBairro(dto.bairro());
        eE.setCep(dto.cep());
        eE.setCidade(cidadeService.findById(dto.idCidade()));

        repository.persist(eE);

        return EnderecoDTOResponse.valueOf(eE);
    }

    @Override
    public EnderecoDTOResponse update(Long id, EnderecoDTORequest dto) throws NotFoundException {
        Endereco eE = repository.findById(id);

        if(eE==null)
        throw new NotFoundException("usuario não encontrado para ser atualizado");

        Endereco e = new Endereco();

        e.setLogradouro(dto.logradouro());
        e.setBairro(dto.bairro());
        e.setCep(dto.cep());
        e.setCidade(cidadeService.findById(dto.idCidade()));

        repository.persist(e);

        return EnderecoDTOResponse.valueOf(e);


    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


}
