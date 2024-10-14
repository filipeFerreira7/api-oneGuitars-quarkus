package br.unitins.tp1.faixas.Usuario.service;
// 
import java.util.List;

import br.unitins.tp1.faixas.Telefone.service.TelefoneService;
import br.unitins.tp1.faixas.Usuario.dto.PessoaFisicaDTORequest;
import br.unitins.tp1.faixas.Usuario.model.PessoaFisica;
import br.unitins.tp1.faixas.Usuario.model.Sexo;
import br.unitins.tp1.faixas.Usuario.repository.PessoaFisicaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class PessoaFisicaServiceImpl implements PessoaFisicaService {

    @Inject
    public PessoaFisicaRepository clienteRepository;

   

    @Inject
    public TelefoneService telefoneService;


    @Override
    public PessoaFisica findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<PessoaFisica> findByNome(String nome){
      return clienteRepository.findByNome(nome);
    }

    
    @Override
    public List<PessoaFisica> findAll() {
      return clienteRepository.findAll().list();
    }

    @Override
    @Transactional
    public PessoaFisica create(PessoaFisicaDTORequest dto) {
     PessoaFisica cliente = new PessoaFisica();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setSexo(Sexo.valueOf(dto.idSexo()));
        clienteRepository.persist(cliente);
        return cliente;
    }

    @Override
    @Transactional
    public PessoaFisica update(Long id , PessoaFisicaDTORequest dto) {
     
      PessoaFisica cliente = clienteRepository.findById(id);
      cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setSexo(Sexo.valueOf(dto.idSexo()));
      clienteRepository.persist(cliente);
       return cliente;
  }
  

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
        
    }
  }
    
