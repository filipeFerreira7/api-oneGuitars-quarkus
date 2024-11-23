package br.unitins.tp1.faixas.Administrador.service;

import java.time.LocalDate;
import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.tp1.faixas.Administrador.dto.AdministradorDTORequest;
import br.unitins.tp1.faixas.Administrador.dto.AdministradorDTOResponse;
import br.unitins.tp1.faixas.Administrador.dto.AdministradorPasswordUpdateDTO;
import br.unitins.tp1.faixas.Administrador.dto.AdministradorUsernameUpdateDTO;
import br.unitins.tp1.faixas.Administrador.model.Administrador;
import br.unitins.tp1.faixas.Administrador.repository.AdministradorRepository;
import br.unitins.tp1.faixas.Hash.service.HashService;
import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTOResponse;
import br.unitins.tp1.faixas.Usuario.model.PessoaFisica;
import br.unitins.tp1.faixas.Usuario.model.Usuario;
import br.unitins.tp1.faixas.Usuario.repository.PessoaFisicaRepository;
import br.unitins.tp1.faixas.Usuario.repository.UsuarioRepository;
import br.unitins.tp1.faixas.Usuario.service.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.xml.bind.ValidationException;

@ApplicationScoped
public class AdministradorServiceImpl implements AdministradorService {
    
    @Inject
    AdministradorRepository repository;

    @Inject
    PessoaFisicaRepository pessoaFisicaRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    UsuarioService usuarioService;

    @Inject
    HashService hashService;

    @Inject
    JsonWebToken jwt;

    @Override
    @Transactional
    public AdministradorDTOResponse create(@Valid AdministradorDTORequest dto) {
        
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        usuario.setSenha(hashService.getHashSenha(dto.senha()));

        usuarioRepository.persist(usuario);


        PessoaFisica pf = new PessoaFisica();
        pf.setNome(dto.nome());
        pf.setTelefone(dto.telefone());
        pf.setDataNascimento(LocalDate.of(dto.anoNasc(),dto.mesNasc(),dto.diaNasc()));
        pf.setCpf(dto.cpf());
        pf.setUsuario(usuario);

        pessoaFisicaRepository.persist(pf);


        Administrador administrador = new Administrador();

        administrador.setCodigoContrato(dto.codigoContrato());
        LocalDate dataAdmissao = LocalDate.of(dto.anoAdmissao(), dto.mesAdmissao(), dto.diaAdmissao());
        administrador.setDataAdmissao(dataAdmissao);
        administrador.setPessoaFisica(pf);

        repository.persist(administrador);
        
        return AdministradorDTOResponse.valueof(administrador);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, AdministradorDTORequest dto) throws ValidationException {
        Usuario usuario = repository.findById(id).getPessoaFisica().getUsuario();
        if(usuario != null){
            usuario.setUsername(dto.username());
            // fazer hash da nova senha
            usuario.setSenha(hashService.getHashSenha(dto.senha()));
        } else {
            throw new ValidationException("Administrador inexistente");
        }

        PessoaFisica pf = repository.findById(id).getPessoaFisica();
        if(pf != null){
            pf.setNome(dto.nome());
            pf.setTelefone(dto.telefone());
            pf.setDataNascimento(LocalDate.of(dto.anoNasc(),dto.mesNasc(),dto.diaNasc()));
            pf.setCpf(dto.cpf());
            pf.setUsuario(usuario);
        } else {
            throw new ValidationException("Administrador inexistente");
        }
        
        Administrador administrador = repository.findById(id);

        if(administrador != null){
            administrador.setCodigoContrato(dto.codigoContrato());
            LocalDate dataAdmissao = LocalDate.of(dto.anoAdmissao(), dto.mesAdmissao(), dto.diaAdmissao());
            administrador.setDataAdmissao(dataAdmissao);
            administrador.setPessoaFisica(pf);
        } else {
            throw new ValidationException("Administrador inexistente");
        }
    }

    @Override
    @Transactional
    public void updateUsuarioPassword(AdministradorPasswordUpdateDTO passwordUpdateDTO) {

        Usuario usuario = usuarioRepository.findById(Long.valueOf(jwt.getClaim("userId").toString()));
        Administrador administrador = repository.findByIdUsuario(usuario.getId());
        if (usuario == null || administrador == null) {
            throw new InternalError();
        }

        if(usuario.getSenha().equals(hashService.getHashSenha(passwordUpdateDTO.oldPassword()))){
            usuario.setSenha(hashService.getHashSenha(passwordUpdateDTO.newPassword()));
            usuarioService.update(usuario);
        }
    }

    @Override
    @Transactional
    public void updateUsuarioUsername(AdministradorUsernameUpdateDTO usernameUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(Long.valueOf(jwt.getClaim("userId").toString()));
        Administrador administrador = repository.findByIdUsuario(usuario.getId());
        if (usuario == null || administrador == null) {
            throw new InternalError();
        }
        administrador.getPessoaFisica().getUsuario().setUsername(usernameUpdateDTO.newUsername());
        usuarioService.update(administrador.getPessoaFisica().getUsuario());
        repository.persist(administrador);
    }

    @Override
    public List<AdministradorDTOResponse> findAll() {
        return repository.findAll()
                                .stream()
                                .map(e -> AdministradorDTOResponse.valueof(e)).toList();
    }

    @Override
    public AdministradorDTOResponse findById(Long id) {

        Administrador cor = repository.findById(id);

        if(cor != null)
            return AdministradorDTOResponse.valueof(repository.findById(id));
        return null;       
    }

    @Override
    public List<AdministradorDTOResponse> findByNome(String nome) {
        return repository.findByNome(nome)
                         .stream()
                         .map(e -> AdministradorDTOResponse.valueof(e)).toList();
    }

    @Override
    public AdministradorDTOResponse findByUsername(String username) {

        Administrador administrador = repository.findByUsername(username);

        if(administrador != null)
            return AdministradorDTOResponse.valueof(administrador);
        return null;       
    }

    @Override
    public AdministradorDTOResponse findByCpf(String cpf) {

        Administrador administrador = repository.findByCpf(cpf);

        if(administrador != null)
            return AdministradorDTOResponse.valueof(administrador);
        return null;       
    }

    @Override
    public UsuarioDTOResponse login(String username, String senha) {
        Administrador administrador = repository.findByUsernameAndSenha(username, senha);
        // verificar se existe ou não
        if(administrador != null)
            return UsuarioDTOResponse.valueOf(administrador.getPessoaFisica().getUsuario());
        return null;
    }

}