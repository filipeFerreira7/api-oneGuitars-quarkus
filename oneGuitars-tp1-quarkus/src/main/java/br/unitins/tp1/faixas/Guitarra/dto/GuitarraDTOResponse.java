package br.unitins.tp1.faixas.Guitarra.dto;

import br.unitins.tp1.faixas.Especificacao.dto.EspecificacaoDTOResponse;
import br.unitins.tp1.faixas.Guitarra.model.Guitarra;
import br.unitins.tp1.faixas.Marca.dto.MarcaDTOResponse;

public record GuitarraDTOResponse(
    Long id,
    String nome,
    String numeroSerie,
    String cor,
    Double preco,
    EspecificacaoDTOResponse especificacao,
    MarcaDTOResponse marca
) {
 public static GuitarraDTOResponse valueOf(Guitarra guitarra){
        return new GuitarraDTOResponse(guitarra.getId(), 
                                       guitarra.getNome(), 
                                       guitarra.getNumeroSerie(),
                                       guitarra.getCor(),
                                       guitarra.getPreco(),
                                       EspecificacaoDTOResponse.valueOf(guitarra.getEspecificacao()),
                                       MarcaDTOResponse.valueOf(guitarra.getMarca())
                                      );
    }
}
