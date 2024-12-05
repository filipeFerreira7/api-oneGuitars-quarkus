package br.unitins.tp1.oneGuitars.Conta.service;

import java.util.List;

import br.unitins.tp1.oneGuitars.Conta.model.Conta;
import br.unitins.tp1.oneGuitars.Conta.repository.ContaRepository;
import br.unitins.tp1.oneGuitars.validation.EntidadeNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ContaServiceImpl implements ContaService {

  @Inject
  EntityManager em;

  @Inject
  ContaRepository repository;

// nao existe resource para esse serviço  
  @Override
  public Conta findByUsernameAndSenha(String username, String senha) {
    Conta conta = repository.findByUsernameAndSenha(username, senha);
    if(conta ==null)
      throw new EntidadeNotFoundException("username e senha", "usuario e senha não encontrados.");

      return conta;
  }

  @Override
  public Conta findById(Long id) {
   return repository.findById(id);
  }

  @Override
  public List<Conta> findByNome(String nome) {
      return repository.findByNome(nome);
  }



  @Override
  public Conta findByCpf(String cpf) {
   Conta conta = repository.findByCpf(cpf);

   if(conta ==null)
      throw new EntidadeNotFoundException("cpf", "cpf não encontrado.");
   
   return conta;
  }



  @Override
  public List<Conta> findAll() {
   
   return repository.findAll().list();
  }

  @Override
  public void update(Conta conta) {
    em.merge(conta);
  }



}