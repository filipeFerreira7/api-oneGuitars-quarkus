package br.unitins.tp1.faixas.DTO;

import br.unitins.tp1.faixas.model.Cliente;
import br.unitins.tp1.faixas.model.Sexo;

public record ClienteDTOResponse(
    Long id,
    String nome,
    String cpf,
    String email,
    TelefoneDTOResponse telefone,
    Sexo sexo
) {
    public static ClienteDTOResponse valueOf(Cliente cliente) {
        return new ClienteDTOResponse(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getEmail(),
            TelefoneDTOResponse.valueOf(cliente.getTelefone()),
            cliente.getSexo()
        );
    }
}
