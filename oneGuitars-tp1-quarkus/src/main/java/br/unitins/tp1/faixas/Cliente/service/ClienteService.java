package br.unitins.tp1.faixas.Cliente.service;
import java.util.List;

import br.unitins.tp1.faixas.Cliente.dto.ClienteDTORequest;
import br.unitins.tp1.faixas.Cliente.dto.ClienteDTOResponse;
import br.unitins.tp1.faixas.Cliente.dto.PasswordUpdateDTO;
import br.unitins.tp1.faixas.Cliente.dto.UsernameUpdateDTO;
import br.unitins.tp1.faixas.Conta.dto.UsuarioDTOResponse;
import br.unitins.tp1.faixas.PessoaFisica.model.PessoaFisica;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ClienteService {

    ClienteDTOResponse findById(Long id);

    ClienteDTOResponse findByCpf(String cpf);

    List<ClienteDTOResponse> findByNome(String nome);

    List<ClienteDTOResponse> findAll();

    ClienteDTOResponse create(ClienteDTORequest dto);

    ClienteDTOResponse update(Long id, ClienteDTORequest dto);

    void delete(Long id);

    public void updatePassword(PasswordUpdateDTO passwordUpdateDTO);
    public void updateUsername(UsernameUpdateDTO usernameUpdateDTO);

    PessoaFisica updateNomeImagem(Long id, String nomeImagem);

    public UsuarioDTOResponse login(String username, String hashSenha);


}
