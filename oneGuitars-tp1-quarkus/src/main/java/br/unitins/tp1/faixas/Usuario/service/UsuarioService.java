package br.unitins.tp1.faixas.Usuario.service;

import br.unitins.tp1.faixas.Usuario.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface UsuarioService {

    Usuario findByUsername(String username);

    void update(Usuario usuario);

}
