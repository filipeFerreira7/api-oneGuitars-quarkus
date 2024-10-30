package br.unitins.tp1.faixas.Marca.dto;

import br.unitins.tp1.faixas.Marca.model.Marca;

public record MarcaDTOResponse(
    Long id,
    String nome
) {
 public static MarcaDTOResponse valueOf(Marca marca ){
        return new MarcaDTOResponse(marca.getId(), 
                                       marca.getNome()
                                      );
    }
}
