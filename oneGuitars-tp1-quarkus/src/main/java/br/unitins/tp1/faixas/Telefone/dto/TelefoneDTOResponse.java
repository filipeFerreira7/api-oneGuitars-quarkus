package br.unitins.tp1.faixas.Telefone.dto;

import br.unitins.tp1.faixas.Telefone.model.Telefone;

public record TelefoneDTOResponse(
    Long id,
    String dd,
    String numero

) {
    public static TelefoneDTOResponse valueOf(Telefone telefone) {
        return new TelefoneDTOResponse(
            telefone.getId(),
            telefone.getDd(),
            telefone.getNumero()

            
        );
    }
}
