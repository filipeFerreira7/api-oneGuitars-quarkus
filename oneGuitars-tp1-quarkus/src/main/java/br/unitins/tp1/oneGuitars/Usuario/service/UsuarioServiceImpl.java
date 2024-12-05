package br.unitins.tp1.oneGuitars.Usuario.service;

import java.time.LocalDate;
// 
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.tp1.oneGuitars.Cidade.service.CidadeService;
import br.unitins.tp1.oneGuitars.Conta.dto.ContaDTOResponse;
import br.unitins.tp1.oneGuitars.Conta.model.Conta;
import br.unitins.tp1.oneGuitars.Conta.model.Perfil;
import br.unitins.tp1.oneGuitars.Conta.repository.ContaRepository;
import br.unitins.tp1.oneGuitars.Conta.service.ContaService;
import br.unitins.tp1.oneGuitars.Endereco.repository.EnderecoRepository;
import br.unitins.tp1.oneGuitars.Hash.service.HashService;
import br.unitins.tp1.oneGuitars.Hash.service.HashServiceImpl;
import br.unitins.tp1.oneGuitars.PessoaFisica.model.PessoaFisica;
import br.unitins.tp1.oneGuitars.PessoaFisica.model.Sexo;
import br.unitins.tp1.oneGuitars.PessoaFisica.repository.PessoaFisicaRepository;
import br.unitins.tp1.oneGuitars.Telefone.service.TelefoneService;
import br.unitins.tp1.oneGuitars.Usuario.dto.PasswordUpdateDTO;
import br.unitins.tp1.oneGuitars.Usuario.dto.UsernameUpdateDTO;
import br.unitins.tp1.oneGuitars.Usuario.dto.UsuarioDTORequest;
import br.unitins.tp1.oneGuitars.Usuario.dto.UsuarioDTOResponse;
import br.unitins.tp1.oneGuitars.Usuario.model.Usuario;
import br.unitins.tp1.oneGuitars.Usuario.repository.UsuarioRepository;
import br.unitins.tp1.oneGuitars.validation.EntidadeNotFoundException;
import br.unitins.tp1.oneGuitars.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
  @Inject
  public EnderecoRepository enderecoRepository;

  @Inject
  public CidadeService cidadeService;

  @Override
  public UsuarioDTOResponse findById(Long id) throws NotFoundException {
    Usuario u = repository.findById(id);

    if (u != null)
      return UsuarioDTOResponse.valueOf(repository.findById(id));

    throw new EntidadeNotFoundException("id", "usuario não encontrado");
  }

  @Override
  public UsuarioDTOResponse findByCpf(String cpf) {
    Usuario usuario = repository.findByCpf(cpf);

    if (usuario == null)
      throw new EntidadeNotFoundException("cpf", "cpf não encontrado.");

    return UsuarioDTOResponse.valueOf(usuario);
  }
  

  @Override
  public List<UsuarioDTOResponse> findByNome(String nome) {
    return repository.findByNome(nome)
        .stream()
        .map(o -> UsuarioDTOResponse.valueOf(o)).toList();
  }

  @Override
  public Usuario findByUsername(String username)throws NotFoundException {
    Usuario u = repository.findByUsername(username);

    if (u != null)
      return repository.findByUsername(username);

    throw new EntidadeNotFoundException("username", "usuario não encontrado");
  }

  @Transactional
  @Override
  public List<UsuarioDTOResponse> findAll() {
    return repository.findAll().stream().map(e -> UsuarioDTOResponse.valueOf(e)).toList();
  }

  @Override
  @Transactional
  public UsuarioDTOResponse create(UsuarioDTORequest dto) {

    if (repository.findByUsername(dto.username()) != null) {
      throw new ValidationException("username","O username já está em uso");
    }

    Conta conta = new Conta();
    HashService hash = new HashServiceImpl();

    conta.setUsername(dto.username());
    conta.setSenha(hash.getHashSenha(dto.senha()));
    // logica de perfis
    List<Perfil> perfis = dto.idPerfis().stream().map(Perfil::valueOf).collect(Collectors.toList());
    conta.setPerfis(perfis);

    contaRepository.persist(conta);
    //criando instancia de pessoaFisica
    PessoaFisica pF = new PessoaFisica();
    pF.setNome(dto.nome());
    pF.setTelefone(telefoneService.create(dto.telefone()));
    pF.setDataNascimento(LocalDate.of(dto.anoNasc(), dto.mesNasc(), dto.diaNasc()));
    pF.setSexo(Sexo.valueOf(dto.idSexo()));
    
    Usuario verificaCpf = repository.findByCpf(dto.cpf());
    if(verificaCpf !=null)
      throw new ValidationException("cpf","Um usuário com este cpf já existe");


    pF.setCpf(dto.cpf());
    pF.setConta(conta);
    
    pessoaFisicaRepository.persist(pF);

    //criando instancia de endereco

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
      throw new EntidadeNotFoundException("id", "usuário referenciado não foi encontrado");

    repository.delete(usuario);

  }

  @Override
  @Transactional
  public UsuarioDTOResponse update(Long id, UsuarioDTORequest dto) {
    Conta conta = repository.findById(id).getPessoaFisica().getConta();
    HashService hash = new HashServiceImpl();

    if (conta != null) {
      conta.setUsername(dto.username());
      conta.setSenha(hash.getHashSenha(dto.senha()));
     List<Perfil> perfis = dto.idPerfis().stream().map(Perfil::valueOf).collect(Collectors.toList());
      conta.setPerfis(perfis);

    } else {
      throw new ValidationException("id","usuario inexistente");
    }

    PessoaFisica pF = repository.findById(id).getPessoaFisica();
    if (pF != null) {
      pF.setNome(dto.nome());
      pF.setTelefone(telefoneService.create(dto.telefone()));
      pF.setDataNascimento(LocalDate.of(dto.anoNasc(), dto.mesNasc(), dto.diaNasc()));
      pF.setCpf(dto.cpf());
      pF.setConta(conta);
    } else {
      throw new ValidationException("pessoaFisica","pessoa fisica inexistente");
    }

    Usuario usuario = repository.findById(id);

    if (usuario != null)
      usuario.setPessoaFisica(pF);
    else {
      throw new ValidationException("id","usuario inexistente");
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
  @Transactional
  public void updatePassword(PasswordUpdateDTO passwordUpdateDTO) {
    Conta conta = contaRepository.findById(Long.valueOf(jwt.getClaim("userId").toString()));
    Usuario usuario = repository.findByIdUsuario(conta.getId());
    if (conta == null || usuario == null) {
      throw new EntidadeNotFoundException("conta ou usuário", "conta ou usuário não encontrado!");
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
      throw new EntidadeNotFoundException("conta ou usuário", "conta ou usuário não encontrado!");
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
      throw new EntidadeNotFoundException("usuário ou senha", "Usuário ou senha inválidos!");
  }
}
