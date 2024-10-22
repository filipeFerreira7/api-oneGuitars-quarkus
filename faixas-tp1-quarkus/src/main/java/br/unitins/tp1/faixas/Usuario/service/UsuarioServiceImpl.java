package br.unitins.tp1.faixas.Usuario.service;

// 
import java.util.List;

import br.unitins.tp1.faixas.Telefone.service.TelefoneService;
import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTORequest;
import br.unitins.tp1.faixas.Usuario.model.Usuario;
import br.unitins.tp1.faixas.Usuario.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

  @Inject
  public UsuarioRepository usuarioRepository;

  @Inject
  public TelefoneService telefoneService;
  @Inject
  PessoaFisicaService pessoaFisicaService;

  @Override
  public Usuario findById(Long id) {
    return usuarioRepository.findById(id);
  }

  @Override
  public List<Usuario> findByNome(String nome) {
    return usuarioRepository.findByNome(nome);
  }

  @Override
  public List<Usuario> findAll() {
    return usuarioRepository.findAll().list();
  }

  @Override
  @Transactional
  public Usuario create(UsuarioDTORequest dto) {
    Usuario usuario = new Usuario();

    usuario.setPessoaFisica(pessoaFisicaService.insertPessoaFisica(dto.pessoaFisicaDto()));
    usuario.setTelefone(telefoneService.create(dto.telefoneDTO()));

    usuarioRepository.persist(usuario);

    return usuario;
  }

  @Override
  @Transactional
  public Usuario update(Long id, UsuarioDTORequest dto) {

    Usuario usuario = usuarioRepository.findById(id);
    usuario.setPessoaFisica(pessoaFisicaService.insertPessoaFisica(dto.pessoaFisicaDto()));
    usuario.setTelefone(telefoneService.create(dto.telefoneDTO()));

    usuarioRepository.persist(usuario);

    return usuario;
  }

  @Override
  @Transactional
  public void delete(Long id) {
    usuarioRepository.deleteById(id);

  }
}
