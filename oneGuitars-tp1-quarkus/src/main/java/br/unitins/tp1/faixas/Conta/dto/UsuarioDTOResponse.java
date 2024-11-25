package br.unitins.tp1.faixas.Conta.dto;

import br.unitins.tp1.faixas.Conta.model.Perfil;
import br.unitins.tp1.faixas.Conta.model.Conta;

public record UsuarioDTOResponse(
    Long id,
    String username,
    String senha,
    Perfil perfil
   
) {
    public static UsuarioDTOResponse valueOf(Conta usuario) {
        return new UsuarioDTOResponse(
            usuario.getId(),
            usuario.getUsername(),
            usuario.getSenha(),
            usuario.getPerfil()
             );
    }
}
