package br.unitins.tp1.faixas.Cliente.dto;

import java.time.LocalDateTime;

import br.unitins.tp1.faixas.Cliente.model.Cliente;
import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTOResponse;
import br.unitins.tp1.faixas.Usuario.model.Perfil;
import br.unitins.tp1.faixas.Usuario.model.Sexo;

public record ClienteDTOResponse(
    Long id,
    String nome,
    String cpf,
    String sexo,
    String username,
    String senha,
    Perfil perfil,
    TelefoneDTOResponse telefone
) {
    public static ClienteDTOResponse valueOf(Cliente cliente) {
        return new ClienteDTOResponse(
            cliente.getId(),
            cliente.getPessoaFisica().getNome(),
            cliente.getPessoaFisica().getCpf(),
            Sexo.valueOf(cliente.getPessoaFisica().getSexo().getId()).getDescricao(),
            cliente.getPessoaFisica().getUsuario().getUsername(),
            cliente.getPessoaFisica().getUsuario().getSenha(),
            cliente.getPessoaFisica().getUsuario().getPerfil(),
            TelefoneDTOResponse.valueOf(cliente.getPessoaFisica().getTelefone()
        ));
    }
}
