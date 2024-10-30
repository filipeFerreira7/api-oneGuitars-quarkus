package br.unitins.tp1.faixas.Usuario.dto;

import br.unitins.tp1.faixas.Usuario.model.PessoaFisica;

public record UsuarioDTOResponse(
    Long id,
    String username,
    String nome
   
) {
    public static UsuarioDTOResponse valueOf(PessoaFisica pf) {
        return new UsuarioDTOResponse(
            pf.getId(),
            pf.getUsuario().getUsername(),
            pf.getNome()
             );
    }
}
