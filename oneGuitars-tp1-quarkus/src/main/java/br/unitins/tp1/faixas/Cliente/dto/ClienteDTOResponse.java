package br.unitins.tp1.faixas.Cliente.dto;

import br.unitins.tp1.faixas.Cliente.model.Cliente;
import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTOResponse;
import br.unitins.tp1.faixas.Usuario.model.Sexo;

public record ClienteDTOResponse(
    Long id,
    String nome,
    String cpf,
    Sexo sexo,
    String username,
    String senha,
    TelefoneDTOResponse telefone
) {
    public static ClienteDTOResponse valueOf(Cliente cliente) {
        return new ClienteDTOResponse(
            cliente.getId(),
            cliente.getPessoaFisica().getNome(),
            cliente.getPessoaFisica().getCpf(),
            cliente.getPessoaFisica().getSexo(),
            cliente.getPessoaFisica().getUsuario().getUsername(),
            cliente.getPessoaFisica().getUsuario().getSenha(),
            TelefoneDTOResponse.valueOf(cliente.getPessoaFisica().getTelefone()
        ));
    }
}
