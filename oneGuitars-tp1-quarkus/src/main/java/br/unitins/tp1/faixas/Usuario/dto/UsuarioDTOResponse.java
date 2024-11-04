package br.unitins.tp1.faixas.Usuario.dto;

import br.unitins.tp1.faixas.Usuario.model.Perfil;
import br.unitins.tp1.faixas.Usuario.model.Usuario;

public record UsuarioDTOResponse(
    Long id,
    String username,
    String senha,
    Perfil perfil
   
) {
    public static UsuarioDTOResponse valueOf(Usuario usuario) {
        return new UsuarioDTOResponse(
            usuario.getId(),
            usuario.getUsername(),
            usuario.getSenha(),
            usuario.getPerfil()
             );
    }
}
