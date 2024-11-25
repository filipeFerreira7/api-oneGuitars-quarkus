package br.unitins.tp1.faixas.Administrador.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.tp1.faixas.Administrador.dto.AdministradorDTORequest;
import br.unitins.tp1.faixas.Administrador.dto.AdministradorDTOResponse;
import br.unitins.tp1.faixas.Administrador.dto.AdministradorPasswordUpdateDTO;
import br.unitins.tp1.faixas.Administrador.dto.AdministradorUsernameUpdateDTO;
import br.unitins.tp1.faixas.Administrador.model.Administrador;
import br.unitins.tp1.faixas.Administrador.repository.AdministradorRepository;
import br.unitins.tp1.faixas.Conta.dto.ContaDTOResponse;
import br.unitins.tp1.faixas.Conta.model.Conta;
import br.unitins.tp1.faixas.Conta.repository.ContaRepository;
import br.unitins.tp1.faixas.Conta.service.ContaService;
import br.unitins.tp1.faixas.Hash.service.HashService;
import br.unitins.tp1.faixas.PessoaFisica.model.PessoaFisica;
import br.unitins.tp1.faixas.PessoaFisica.repository.PessoaFisicaRepository;
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
    ContaRepository contaRepository;

    @Inject
    ContaService contaService;

    @Inject
    HashService hashService;

    @Inject
    JsonWebToken jwt;

    @Override
    @Transactional
    public AdministradorDTOResponse create(@Valid AdministradorDTORequest dto) {
        // conta
        Conta conta = new Conta();
        conta.setUsername(dto.username());
        conta.setSenha(hashService.getHashSenha(dto.senha()));
        contaRepository.persist(conta);
        // pessoaFisica
        PessoaFisica pf = new PessoaFisica();
        pf.setNome(dto.nome());
        pf.setTelefone(dto.telefone());
        pf.setDataNascimento(LocalDate.of(dto.anoNasc(),dto.mesNasc(),dto.diaNasc()));
        pf.setCpf(dto.cpf());
        pf.setConta(conta);

        pessoaFisicaRepository.persist(pf);

        //Administrador
        Administrador administrador = new Administrador();

        administrador.setPessoaFisica(pf);
         //Setando um codigo unico de adm
         administrador.setCodigoAdm(UUID.randomUUID().toString());

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
        Conta conta = repository.findById(id).getPessoaFisica().getConta();
        if(conta != null){
            conta.setUsername(dto.username());
            // fazer hash da nova senha
            conta.setSenha(hashService.getHashSenha(dto.senha()));
        } else {
            throw new ValidationException("Administrador inexistente");
        }

        PessoaFisica pf = repository.findById(id).getPessoaFisica();
        if(pf != null){
            pf.setNome(dto.nome());
            pf.setTelefone(dto.telefone());
            pf.setDataNascimento(LocalDate.of(dto.anoNasc(),dto.mesNasc(),dto.diaNasc()));
            pf.setCpf(dto.cpf());
            pf.setConta(conta);
        } else {
            throw new ValidationException("Administrador inexistente");
        }
        
        Administrador administrador = repository.findById(id);

        if(administrador != null){
           
            administrador.setPessoaFisica(pf);
        } else {
            throw new ValidationException("Administrador inexistente");
        }
    }

    @Override
    @Transactional
    public void updateContaPassword(AdministradorPasswordUpdateDTO passwordUpdateDTO) {

        Conta conta = contaRepository.findById(Long.valueOf(jwt.getClaim("userId").toString()));
        Administrador administrador = repository.findByIdUsuario(conta.getId());
        if (conta == null || administrador == null) {
            throw new InternalError();
        }

        if(conta.getSenha().equals(hashService.getHashSenha(passwordUpdateDTO.oldPassword()))){
            conta.setSenha(hashService.getHashSenha(passwordUpdateDTO.newPassword()));
            contaService.update(conta);
        }
    }

    @Override
    @Transactional
    public void updateContaUsername(AdministradorUsernameUpdateDTO usernameUpdateDTO) {
        Conta conta = contaRepository.findById(Long.valueOf(jwt.getClaim("userId").toString()));
        Administrador administrador = repository.findByIdUsuario(conta.getId());
        if (conta == null || administrador == null) {
            throw new InternalError();
        }
        administrador.getPessoaFisica().getConta().setUsername(usernameUpdateDTO.newUsername());
        contaService.update(administrador.getPessoaFisica().getConta());
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
    public ContaDTOResponse login(String username, String senha) {
        Administrador administrador = repository.findByUsernameAndSenha(username, senha);
        // verificar se existe ou não
        if(administrador != null)
            return ContaDTOResponse.valueOf(administrador.getPessoaFisica().getConta());
        return null;
    }

}