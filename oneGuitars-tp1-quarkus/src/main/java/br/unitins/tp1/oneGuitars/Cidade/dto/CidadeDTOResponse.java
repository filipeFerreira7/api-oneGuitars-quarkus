package br.unitins.tp1.oneGuitars.Cidade.dto;

import br.unitins.tp1.oneGuitars.Cidade.model.Cidade;
import br.unitins.tp1.oneGuitars.Estado.dto.EstadoDTOResponse;

public record CidadeDTOResponse (
    Long id,
    String nome,
    EstadoDTOResponse estado
){
    public static CidadeDTOResponse valueOf(Cidade cidade){
            return new CidadeDTOResponse(
                        cidade.getId(),
                         cidade.getNome(),
                         EstadoDTOResponse.valueOf(cidade.getEstado()));
    }
}
