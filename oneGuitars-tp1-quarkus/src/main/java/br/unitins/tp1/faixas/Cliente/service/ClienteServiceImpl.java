package br.unitins.tp1.faixas.Cliente.service;

import java.time.LocalDate;
// 
import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.tp1.faixas.Cliente.dto.ClienteDTORequest;
import br.unitins.tp1.faixas.Cliente.dto.ClienteDTOResponse;
import br.unitins.tp1.faixas.Cliente.dto.PasswordUpdateDTO;
import br.unitins.tp1.faixas.Cliente.dto.UsernameUpdateDTO;
import br.unitins.tp1.faixas.Cliente.model.Cliente;
import br.unitins.tp1.faixas.Cliente.repository.ClienteRepository;
import br.unitins.tp1.faixas.Conta.dto.UsuarioDTOResponse;
import br.unitins.tp1.faixas.Conta.model.Perfil;
import br.unitins.tp1.faixas.Conta.model.Conta;
import br.unitins.tp1.faixas.Conta.repository.ContaRepository;
import br.unitins.tp1.faixas.Conta.service.UsuarioService;
import br.unitins.tp1.faixas.Hash.service.HashService;
import br.unitins.tp1.faixas.Hash.service.HashServiceImpl;
import br.unitins.tp1.faixas.PessoaFisica.model.PessoaFisica;
import br.unitins.tp1.faixas.PessoaFisica.model.Sexo;
import br.unitins.tp1.faixas.PessoaFisica.repository.PessoaFisicaRepository;
import br.unitins.tp1.faixas.Telefone.service.TelefoneService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

  @Inject
  HashService hashService;

  @Inject
  JsonWebToken jwt;

  @Inject
  public ClienteRepository repository;

  @Inject
  public TelefoneService telefoneService;

  @Inject
  PessoaFisicaRepository pessoaFisicaRepository;

  @Inject
  public ContaRepository usuarioRepository;
  @Inject
  public UsuarioService usuarioService;

  @Override
  public ClienteDTOResponse findById(Long id) throws NotFoundException {
    Cliente c = repository.findById(id);

    if (c != null)
      return ClienteDTOResponse.valueOf(repository.findById(id));

    return null;
  }

  @Override
  public ClienteDTOResponse findByCpf(String cpf) {
    Cliente cliente = repository.findByCpf(cpf);

    if (cliente == null)
      return null;

    return ClienteDTOResponse.valueOf(cliente);
  }

  @Override
  public List<ClienteDTOResponse> findByNome(String nome) {
    return repository.findByNome(nome)
        .stream()
        .map(o -> ClienteDTOResponse.valueOf(o)).toList();
  }

  @Transactional
  @Override
  public List<ClienteDTOResponse> findAll() {
    return repository.findAll().stream().map(e -> ClienteDTOResponse.valueOf(e)).toList();
  }

  @Override
  @Transactional
  public ClienteDTOResponse create(ClienteDTORequest dto) {
    Conta usuario = new Conta();
    HashService hash = new HashServiceImpl();

    usuario.setUsername(dto.username());
    usuario.setSenha(hash.getHashSenha(dto.senha()));
    usuario.setPerfil(Perfil.valueOf(dto.idPerfil()));

    usuarioRepository.persist(usuario);

    PessoaFisica pF = new PessoaFisica();
    pF.setNome(dto.nome());
    pF.setTelefone(telefoneService.create(dto.telefone()));
    pF.setDataNascimento(LocalDate.of(dto.anoNasc(), dto.mesNasc(), dto.diaNasc()));
    pF.setSexo(Sexo.valueOf(dto.idSexo()));
    pF.setCpf(dto.cpf());
    pF.setUsuario(usuario);

    pessoaFisicaRepository.persist(pF);

    Cliente cliente = new Cliente();
    cliente.setPessoaFisica(pF);

    repository.persist(cliente);

    return ClienteDTOResponse.valueOf(cliente);
  }

  @Override
  @Transactional
  public void delete(Long id) throws IllegalArgumentException, NotFoundException {

    if (id == null)
      throw new IllegalArgumentException("Numero inválido");

    Cliente cliente = repository.findById(id);

    if (cliente == null)
      throw new NotFoundException("Nenhum usuário encontrado!");

    repository.delete(cliente);

  }

  @Override
  @Transactional
  public ClienteDTOResponse update(Long id, ClienteDTORequest dto) throws ValidationException {
    Conta usuario = repository.findById(id).getPessoaFisica().getUsuario();
    HashService hash = new HashServiceImpl();

    if (usuario != null) {
      usuario.setUsername(dto.username());
      usuario.setSenha(hash.getHashSenha(dto.senha()));
      usuario.setPerfil(Perfil.valueOf(dto.idPerfil()));
    } else {
      throw new ValidationException("Cliente inexistente");
    }

    PessoaFisica pF = repository.findById(id).getPessoaFisica();
    if (pF != null) {
      pF.setNome(dto.nome());
      pF.setTelefone(telefoneService.create(dto.telefone()));
      pF.setDataNascimento(LocalDate.of(dto.anoNasc(), dto.mesNasc(), dto.diaNasc()));
      pF.setCpf(dto.cpf());
      pF.setUsuario(usuario);
    } else {
      throw new ValidationException("Cliente inexistente");
    }

    Cliente cliente = repository.findById(id);

    if (cliente != null)
      cliente.setPessoaFisica(pF);
    else {
      throw new ValidationException("Cliente inexistente");
    }

    repository.persist(cliente);

    return ClienteDTOResponse.valueOf(cliente);
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
    Conta usuario = usuarioRepository.findById(Long.valueOf(jwt.getClaim("userId").toString()));
    Cliente cliente = repository.findByIdUsuario(usuario.getId());
    if (usuario == null || cliente == null) {
      throw new InternalError();
    }
    if (usuario.getSenha().equals(hashService.getHashSenha(passwordUpdateDTO.senhaAntiga()))) {
        usuario.setSenha(hashService.getHashSenha(passwordUpdateDTO.senhaNova()));
        usuarioService.update(usuario);
    }
  }

  @Override
  @Transactional
  public void updateUsername(UsernameUpdateDTO usernameUpdateDTO) {
    Conta usuario = usuarioRepository.findById(Long.valueOf(jwt.getClaim("userId").toString()));
    Cliente cliente = repository.findByIdUsuario(usuario.getId());
    if (usuario == null || cliente == null) {
      throw new InternalError();
    }

    cliente.getPessoaFisica().getUsuario().setUsername(usernameUpdateDTO.novoUsername());
    usuarioService.update(cliente.getPessoaFisica().getUsuario());
    repository.persist(cliente);
  }

  @Override
    public UsuarioDTOResponse login(String username, String senha) {
        Cliente cliente = repository.findByUsernameAndSenha(username, senha);
        // verificar se existe ou não
        if(cliente != null)
            return UsuarioDTOResponse.valueOf(cliente.getPessoaFisica().getUsuario());
        return null;
    }
}
