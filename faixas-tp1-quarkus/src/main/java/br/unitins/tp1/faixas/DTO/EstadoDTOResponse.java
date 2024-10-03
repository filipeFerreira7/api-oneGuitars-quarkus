package br.unitins.tp1.faixas.DTO;

import br.unitins.tp1.faixas.model.Estado;

public record EstadoDTOResponse(
    Long id,
    String nome,
    String sigla
) {
    public static EstadoDTOResponse valueOf(Estado estado){
        return new EstadoDTOResponse(estado.getId(),estado.getNome(),estado.getSigla());
    }
}
