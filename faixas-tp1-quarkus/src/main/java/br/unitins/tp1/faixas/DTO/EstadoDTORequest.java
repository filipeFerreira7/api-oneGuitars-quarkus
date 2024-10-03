package br.unitins.tp1.faixas.DTO;

import br.unitins.tp1.faixas.model.Estado;

public record EstadoDTORequest(
    String nome,
    String sigla
) {
     public static EstadoDTORequest valueOf(Estado estado){
        return new EstadoDTORequest( estado.getNome(), 
                                       estado.getSigla()
                                      );
    }
    
}
