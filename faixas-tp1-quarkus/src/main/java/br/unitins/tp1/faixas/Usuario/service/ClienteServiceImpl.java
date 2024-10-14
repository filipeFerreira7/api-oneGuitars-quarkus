package br.unitins.tp1.faixas.Usuario.service;
// 
import java.util.List;

import br.unitins.tp1.faixas.Telefone.service.TelefoneService;
import br.unitins.tp1.faixas.Usuario.dto.ClienteDTORequest;
import br.unitins.tp1.faixas.Usuario.model.Cliente;
import br.unitins.tp1.faixas.Usuario.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    public ClienteRepository clienteRepository;

   

    @Inject
    public TelefoneService telefoneService;


    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> findByNome(String nome){
      return clienteRepository.findByNome(nome);
    }

    
    @Override
    public List<Cliente> findAll() {
      return clienteRepository.findAll().list();
    }

    @Override
    @Transactional
    public Cliente create(ClienteDTORequest dto) {
     Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setEmail(dto.email());
        cliente.setTelefone(telefoneService.findById(dto.idTelefone()));
        cliente.setSexo(dto.sexo());
        clienteRepository.persist(cliente);
        return cliente;
    }

    @Override
    @Transactional
    public Cliente update(Long id , ClienteDTORequest dto) {
     
      Cliente cliente = clienteRepository.findById(id);
      cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setEmail(dto.email());
        cliente.setTelefone(telefoneService.findById(dto.idTelefone()));
        cliente.setSexo(dto.sexo());

      clienteRepository.persist(cliente);
       return cliente;
  }
  

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
        
    }
  }
    
