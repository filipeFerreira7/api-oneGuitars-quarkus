package br.unitins.tp1.oneGuitars.Guitarra.dto;

import br.unitins.tp1.oneGuitars.Especificacao.dto.EspecificacaoDTOResponse;
import br.unitins.tp1.oneGuitars.Guitarra.model.Guitarra;
import br.unitins.tp1.oneGuitars.Marca.dto.MarcaDTOResponse;

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
