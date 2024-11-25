package br.unitins.tp1.faixas.Conta.service;

import java.util.List;

import br.unitins.tp1.faixas.Conta.model.Conta;
import br.unitins.tp1.faixas.Conta.repository.ContaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ContaServiceImpl implements ContaService {

  @Inject
  EntityManager em;

  @Inject
  ContaRepository repository;

  
  @Override
  public Conta findByUsernameAndSenha(String username, String senha) {
      return repository.findByUsernameAndSenha(username, senha);
  }

 

  @Override
  public Conta findById(Long id) {
   return repository.findById(id);
  }



  @Override
  public List<Conta> findByNome(String nome) {
      return null;
  }



  @Override
  public List<Conta> findByCpf(String cpf) {
   return null;
  }



  @Override
  public List<Conta> findAll() {
   
   return repository.findAll().list();
  }



  @Override
  public void delete(Long id) {
  
  }



  @Override
  public void update(Conta conta) {
    em.merge(conta);
  }



}