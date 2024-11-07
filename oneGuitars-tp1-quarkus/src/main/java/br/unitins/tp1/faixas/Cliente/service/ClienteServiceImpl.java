package br.unitins.tp1.faixas.Cliente.service;

import java.time.LocalDate;
// 
import java.util.List;

import br.unitins.tp1.faixas.Cliente.dto.ClienteDTORequest;
import br.unitins.tp1.faixas.Cliente.dto.ClienteDTOResponse;
import br.unitins.tp1.faixas.Cliente.model.Cliente;
import br.unitins.tp1.faixas.Cliente.repository.ClienteRepository;
import br.unitins.tp1.faixas.Hash.service.HashService;
import br.unitins.tp1.faixas.Hash.service.HashServiceImpl;
import br.unitins.tp1.faixas.Telefone.service.TelefoneService;
import br.unitins.tp1.faixas.Usuario.model.Perfil;
import br.unitins.tp1.faixas.Usuario.model.PessoaFisica;
import br.unitins.tp1.faixas.Usuario.model.Sexo;
import br.unitins.tp1.faixas.Usuario.model.Usuario;
import br.unitins.tp1.faixas.Usuario.repository.PessoaFisicaRepository;
import br.unitins.tp1.faixas.Usuario.repository.UsuarioRepository;
import br.unitins.tp1.faixas.Usuario.service.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

  @Inject
  public ClienteRepository repository;

  @Inject
  public TelefoneService telefoneService;

  @Inject
  PessoaFisicaRepository pessoaFisicaRepository;

  @Inject
  public UsuarioRepository usuarioRepository;
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
    Usuario usuario = new Usuario();
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
    Usuario usuario = repository.findById(id).getPessoaFisica().getUsuario();
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
}
