package br.unitins.tp1.faixas.DTO;

import br.unitins.tp1.faixas.model.Cidade;

public record CidadeResponseDTO (
    Long id,
    String nome,
    EstadoDTOResponse estado
){
    public static CidadeResponseDTO valueOf(Cidade cidade){
        return new CidadeResponseDTO(
            cidade.getId(), cidade.getNome(),
            EstadoDTOResponse.valueOf(cidade.getEstado())
            );
    }
}
