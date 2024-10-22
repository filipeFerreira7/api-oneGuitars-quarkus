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
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

  @Inject
  public UsuarioRepository usuarioRepository;

  @Inject
  public TelefoneService telefoneService;
  @Inject
  PessoaFisicaService pessoaFisicaService;

  @Override
  public Usuario findById(Long id) throws NotFoundException{
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
  public void delete(Long id) throws IllegalArgumentException, NotFoundException {
  
    if(id==null)  
      throw new IllegalArgumentException("Numero inválido");

      Usuario usuario = usuarioRepository.findById(id);

      if(usuarioRepository.isPersistent(usuario))
            usuarioRepository.delete(usuario);

      else
      throw new NotFoundException("Nenhum usuário encontrado");

  }
}
