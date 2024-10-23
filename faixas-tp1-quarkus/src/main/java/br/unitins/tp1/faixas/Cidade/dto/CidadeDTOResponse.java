package br.unitins.tp1.faixas.Cidade.dto;

import br.unitins.tp1.faixas.Estado.dto.EstadoDTOResponse;
import br.unitins.tp1.faixas.Cidade.model.Cidade;

public record CidadeDTOResponse (
    Long id,
    String nome,
    EstadoDTOResponse estado
){
    public static CidadeDTOResponse valueOf(Cidade cidade){
            return new CidadeDTOResponse(
                        cidade.getId(),
                         cidade.getNome(),
                         EstadoDTOResponse.valueOf(cidade.getEstado())
            );
    }
}
