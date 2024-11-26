package br.unitins.tp1.faixas.Usuario.service;

import java.time.LocalDate;
import java.util.ArrayList;
// 
import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.tp1.faixas.Conta.dto.ContaDTOResponse;
import br.unitins.tp1.faixas.Conta.model.Conta;
import br.unitins.tp1.faixas.Conta.model.Perfil;
import br.unitins.tp1.faixas.Conta.repository.ContaRepository;
import br.unitins.tp1.faixas.Conta.service.ContaService;
import br.unitins.tp1.faixas.Hash.service.HashService;
import br.unitins.tp1.faixas.Hash.service.HashServiceImpl;
import br.unitins.tp1.faixas.PessoaFisica.model.PessoaFisica;
import br.unitins.tp1.faixas.PessoaFisica.model.Sexo;
import br.unitins.tp1.faixas.PessoaFisica.repository.PessoaFisicaRepository;
import br.unitins.tp1.faixas.Telefone.service.TelefoneService;
import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTORequest;
import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTOResponse;
import br.unitins.tp1.faixas.Usuario.dto.PasswordUpdateDTO;
import br.unitins.tp1.faixas.Usuario.dto.UsernameUpdateDTO;
import br.unitins.tp1.faixas.Usuario.model.Usuario;
import br.unitins.tp1.faixas.Usuario.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

  @Inject
  HashService hashService;

  @Inject
  JsonWebToken jwt;

  @Inject
  public UsuarioRepository repository;

  @Inject
  public TelefoneService telefoneService;

  @Inject
  PessoaFisicaRepository pessoaFisicaRepository;

  @Inject
  public ContaRepository contaRepository;
  @Inject
  public ContaService contaService;

  @Override
  public UsuarioDTOResponse findById(Long id) throws NotFoundException {
    Usuario u = repository.findById(id);

    if (u != null)
      return UsuarioDTOResponse.valueOf(repository.findById(id));

    return null;
  }

  @Override
  public UsuarioDTOResponse findByCpf(String cpf) {
    Usuario usuario = repository.findByCpf(cpf);

    if (usuario == null)
      return null;

    return UsuarioDTOResponse.valueOf(usuario);
  }

  @Override
  public List<UsuarioDTOResponse> findByNome(String nome) {
    return repository.findByNome(nome)
        .stream()
        .map(o -> UsuarioDTOResponse.valueOf(o)).toList();
  }

  @Transactional
  @Override
  public List<UsuarioDTOResponse> findAll() {
    return repository.findAll().stream().map(e -> UsuarioDTOResponse.valueOf(e)).toList();
  }

  @Override
  @Transactional
  public UsuarioDTOResponse create(UsuarioDTORequest dto) {
    Conta conta = new Conta();
    HashService hash = new HashServiceImpl();

    conta.setUsername(dto.username());
    conta.setSenha(hash.getHashSenha(dto.senha()));
    // logica de perfis
    List<Perfil> perfis = new ArrayList<>();
    Perfil perfil = Perfil.valueOf(dto.idPerfil());
    Perfil perfil2 = Perfil.valueOf(dto.idPerfil());
    perfis.add(perfil);
    perfis.add(perfil2);

    conta.setPerfis(perfis);

    contaRepository.persist(conta);

    PessoaFisica pF = new PessoaFisica();
    pF.setNome(dto.nome());
    pF.setTelefone(telefoneService.create(dto.telefone()));
    pF.setDataNascimento(LocalDate.of(dto.anoNasc(), dto.mesNasc(), dto.diaNasc()));
    pF.setSexo(Sexo.valueOf(dto.idSexo()));
    pF.setCpf(dto.cpf());
    pF.setConta(conta);

    pessoaFisicaRepository.persist(pF);

    Usuario usuario = new Usuario();
    usuario.setPessoaFisica(pF);

    repository.persist(usuario);

    return UsuarioDTOResponse.valueOf(usuario);
  }

  @Override
  @Transactional
  public void delete(Long id) throws IllegalArgumentException, NotFoundException {

    if (id == null)
      throw new IllegalArgumentException("Numero inválido");

    Usuario usuario = repository.findById(id);

    if (usuario == null)
      throw new NotFoundException("Nenhum usuário encontrado!");

    repository.delete(usuario);

  }

  @Override
  @Transactional
  public UsuarioDTOResponse update(Long id, UsuarioDTORequest dto) throws ValidationException {
    Conta conta = repository.findById(id).getPessoaFisica().getConta();
    HashService hash = new HashServiceImpl();

    if (conta != null) {
      conta.setUsername(dto.username());
      conta.setSenha(hash.getHashSenha(dto.senha()));

      List<Perfil> perfis = new ArrayList<>();
      Perfil perfil = Perfil.valueOf(dto.idPerfil());
      perfis.add(perfil);
      conta.setPerfis(perfis);

    } else {
      throw new ValidationException("Usuario inexistente");
    }

    PessoaFisica pF = repository.findById(id).getPessoaFisica();
    if (pF != null) {
      pF.setNome(dto.nome());
      pF.setTelefone(telefoneService.create(dto.telefone()));
      pF.setDataNascimento(LocalDate.of(dto.anoNasc(), dto.mesNasc(), dto.diaNasc()));
      pF.setCpf(dto.cpf());
      pF.setConta(conta);
    } else {
      throw new ValidationException("Usuario inexistente");
    }

    Usuario usuario = repository.findById(id);

    if (usuario != null)
      usuario.setPessoaFisica(pF);
    else {
      throw new ValidationException("Usuario inexistente");
    }

    repository.persist(usuario);

    return UsuarioDTOResponse.valueOf(usuario);
  }

  @Override
  @Transactional
  public PessoaFisica updateNomeImagem(Long id, String nomeImagem) {
    PessoaFisica pessoafisica = pessoaFisicaRepository.findById(id);

    pessoafisica.setNomeImagem(nomeImagem);

    return pessoafisica;
  }

  @Override
  public void updatePassword(PasswordUpdateDTO passwordUpdateDTO) {
    Conta conta = contaRepository.findById(Long.valueOf(jwt.getClaim("userId").toString()));
    Usuario usuario = repository.findByIdUsuario(conta.getId());
    if (conta == null || usuario == null) {
      throw new InternalError();
    }
    if (conta.getSenha().equals(hashService.getHashSenha(passwordUpdateDTO.senhaAntiga()))) {
      conta.setSenha(hashService.getHashSenha(passwordUpdateDTO.senhaNova()));
      contaService.update(conta);
    }
  }

  @Override
  @Transactional
  public void updateUsername(UsernameUpdateDTO usernameUpdateDTO) {
    Conta conta = contaRepository.findById(Long.valueOf(jwt.getClaim("userId").toString()));
    Usuario usuario = repository.findByIdUsuario(conta.getId());
    if (conta == null || usuario == null) {
      throw new InternalError();
    }

    usuario.getPessoaFisica().getConta().setUsername(usernameUpdateDTO.novoUsername());
    contaService.update(usuario.getPessoaFisica().getConta());
    repository.persist(usuario);
  }

  @Override
  public ContaDTOResponse login(String username, String senha) {
    Usuario usuario = repository.findByUsernameAndSenha(username, senha);
    // verificar se existe ou não
    if (usuario != null)
      return ContaDTOResponse.valueOf(usuario.getPessoaFisica().getConta());
    return null;
  }
}
