package br.unitins.tp1.faixas.Cidade.dto;

import br.unitins.tp1.faixas.Estado.dto.EstadoDTOResponse;
import br.unitins.tp1.faixas.Cidade.model.Cidade;

public record CidadeResponseDTO (
    Long id,
    String nome,
    EstadoDTOResponse estado
){
    public static CidadeResponseDTO valueOf(Cidade cidade){
            return new CidadeResponseDTO(
                        cidade.getId(),
                         cidade.getNome(),
                         EstadoDTOResponse.valueOf(cidade.getEstado())
            );
    }
}
