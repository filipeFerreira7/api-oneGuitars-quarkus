package br.unitins.tp1.faixas.Usuario.service;

import java.util.List;

import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTORequest;
import br.unitins.tp1.faixas.Usuario.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface UsuarioService {

    Usuario findById(Long id);

    List<Usuario> findByNome(String nome);

    List<Usuario> findAll();

    Usuario create(UsuarioDTORequest dto);

    Usuario update(Long id, UsuarioDTORequest dto);

    void delete(Long id);

}
