package br.unitins.tp1.faixas.Usuario.service;
import java.util.List;

import br.unitins.tp1.faixas.Conta.dto.ContaDTOResponse;
import br.unitins.tp1.faixas.PessoaFisica.model.PessoaFisica;
import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTORequest;
import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTOResponse;
import br.unitins.tp1.faixas.Usuario.dto.PasswordUpdateDTO;
import br.unitins.tp1.faixas.Usuario.dto.UsernameUpdateDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface UsuarioService {

    UsuarioDTOResponse findById(Long id);

    UsuarioDTOResponse findByCpf(String cpf);

    List<UsuarioDTOResponse> findByNome(String nome);

    List<UsuarioDTOResponse> findAll();

    UsuarioDTOResponse create(UsuarioDTORequest dto);

    UsuarioDTOResponse update(Long id, UsuarioDTORequest dto);

    void delete(Long id);

    public void updatePassword(PasswordUpdateDTO passwordUpdateDTO);
    public void updateUsername(UsernameUpdateDTO usernameUpdateDTO);

    PessoaFisica updateNomeImagem(Long id, String nomeImagem);

    public ContaDTOResponse login(String username, String hashSenha);


}
