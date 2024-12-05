package br.unitins.tp1.oneGuitars.Conta.dto;

import java.util.List;

import br.unitins.tp1.oneGuitars.Conta.model.Conta;
import br.unitins.tp1.oneGuitars.Conta.model.Perfil;

public record ContaDTOResponse(
    Long id,
    String username,
    String senha,
    List<Perfil> perfis
   
) {
    public static ContaDTOResponse valueOf(Conta conta) {
        return new ContaDTOResponse(
            conta.getId(),
            conta.getUsername(),
            conta.getSenha(),
            conta.getPerfis()
             );
    }
}
