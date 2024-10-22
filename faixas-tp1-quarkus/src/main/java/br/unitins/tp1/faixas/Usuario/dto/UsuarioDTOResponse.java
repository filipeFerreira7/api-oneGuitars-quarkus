package br.unitins.tp1.faixas.Usuario.dto;

import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTOResponse;
import br.unitins.tp1.faixas.Usuario.model.Usuario;

public record UsuarioDTOResponse(
    Long id,
    String nome,
    String cpf,
    String email,
 TelefoneDTOResponse telefone
) {
    public static UsuarioDTOResponse valueOf(Usuario usuario) {
        return new UsuarioDTOResponse(
            usuario.getId(),
            usuario.getPessoaFisica().getNome(),
            usuario.getPessoaFisica().getCpf(),
            usuario.getPessoaFisica().getEmail(),
            TelefoneDTOResponse.valueOf(usuario.getTelefone())
        );
    }
}
