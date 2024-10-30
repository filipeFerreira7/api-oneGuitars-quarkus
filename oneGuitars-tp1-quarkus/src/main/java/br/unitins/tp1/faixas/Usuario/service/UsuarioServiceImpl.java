package br.unitins.tp1.faixas.Usuario.service;

import br.unitins.tp1.faixas.Usuario.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

  @Inject
  EntityManager em;

  @Override
  public Usuario findByUsername(String username) {
    try {
      return em.createQuery("SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class)
          .setParameter("username", username)
          .getSingleResult();
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public void update(Usuario usuario) {
    em.merge(usuario);
  }

}