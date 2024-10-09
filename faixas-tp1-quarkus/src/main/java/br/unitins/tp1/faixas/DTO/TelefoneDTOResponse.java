package br.unitins.tp1.faixas.DTO;

import br.unitins.tp1.faixas.model.Telefone;

public record TelefoneDTOResponse(
    String dd,
    String numero
) {
    public static TelefoneDTOResponse valueOf(Telefone telefone) {
        return new TelefoneDTOResponse(
            telefone.getDd(),
            telefone.getNumero()

            
        );
    }
}
