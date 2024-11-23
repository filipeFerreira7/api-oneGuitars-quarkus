package br.unitins.tp1.faixas.Usuario.service;

import java.util.List;

import br.unitins.tp1.faixas.Usuario.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface UsuarioService {

    Usuario findById(Long id);

    Usuario findByUsernameAndSenha(String username, String senha);

    public void update(Usuario usuario);

    List<Usuario> findByNome(String nome);

    List<Usuario> findByCpf(String cpf);

    List<Usuario> findAll();

    void delete(Long id);
}
