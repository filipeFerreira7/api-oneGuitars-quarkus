package br.unitins.tp1.faixas.EnderecoEntrega.service;

import java.util.List;

import br.unitins.tp1.faixas.Cidade.repository.CidadeRepository;
import br.unitins.tp1.faixas.Cidade.service.CidadeService;
import br.unitins.tp1.faixas.EnderecoEntrega.dto.EnderecoEntregaDTORequest;
import br.unitins.tp1.faixas.EnderecoEntrega.dto.EnderecoEntregaDTOResponse;
import br.unitins.tp1.faixas.EnderecoEntrega.model.EnderecoEntrega;
import br.unitins.tp1.faixas.EnderecoEntrega.repository.EnderecoEntregaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EnderecoEntregaServiceImpl implements EnderecoEntregaService {

    @Inject
    EnderecoEntregaRepository repository;

    @Inject
    CidadeRepository cidadeRepository;

    @Inject
    CidadeService cidadeService;

    @Override
    public EnderecoEntrega findById(Long id) throws NotFoundException{
        EnderecoEntrega e = repository.findById(id);
        
        if(e == null)
            throw new NotFoundException("Endereco Entrega não encontrado");
        return repository.findById(id);
    }

    @Override
    public EnderecoEntrega findByCep(String cep) throws NotFoundException{
        EnderecoEntrega e = repository.findByCep(cep);

        if(e==null)
        throw new NotFoundException("Cep do endereco não encontrado");
    
        return repository.findByCep(cep);
    }

    @Transactional
    @Override
    public List<EnderecoEntregaDTOResponse> findAll(){
        return repository.findAll().stream().map(e -> EnderecoEntregaDTOResponse.valueOf(e)).toList();
    }

    @Override
    @Transactional
    public EnderecoEntregaDTOResponse create(EnderecoEntregaDTORequest dto){
        EnderecoEntrega eE = new EnderecoEntrega();
        eE.setLogradouro(dto.logradouro());
        eE.setBairro(dto.bairro());
        eE.setCep(dto.cep());
        eE.setCidade(cidadeService.findById(dto.idCidade()));

        repository.persist(eE);

        return EnderecoEntregaDTOResponse.valueOf(eE);
    }

    @Override
    public EnderecoEntregaDTOResponse update(Long id, EnderecoEntregaDTORequest dto) throws NotFoundException {
        EnderecoEntrega eE = repository.findById(id);

        if(eE==null)
        throw new NotFoundException("usuario não encontrado para ser atualizado");

        EnderecoEntrega e = new EnderecoEntrega();

        e.setLogradouro(dto.logradouro());
        e.setBairro(dto.bairro());
        e.setCep(dto.cep());
        e.setCidade(cidadeService.findById(dto.idCidade()));

        repository.persist(e);

        return EnderecoEntregaDTOResponse.valueOf(e);


    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


}
