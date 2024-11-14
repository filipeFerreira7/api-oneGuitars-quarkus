package br.unitins.tp1.faixas.Usuario.service;

import java.util.List;

import br.unitins.tp1.faixas.Usuario.model.Usuario;
import br.unitins.tp1.faixas.Usuario.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

  @Inject
  EntityManager em;

  @Inject
  UsuarioRepository repository;

  
  @Override
  public Usuario findByUsernameAndSenha(String username, String senha) {
      return repository.findByUsernameAndSenha(username, senha);
  }

 

  @Override
  public Usuario findById(Long id) {
   return repository.findById(id);
  }



  @Override
  public List<Usuario> findByNome(String nome) {
      return null;
  }



  @Override
  public List<Usuario> findByCpf(String cpf) {
   return null;
  }



  @Override
  public List<Usuario> findAll() {
   
   return repository.findAll().list();
  }



  @Override
  public void delete(Long id) {
  
  }

}