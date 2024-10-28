package br.unitins.tp1.faixas.Lote.dto;

import java.time.LocalDate;

import br.unitins.tp1.faixas.Guitarra.dto.GuitarraDTOResponse;
import br.unitins.tp1.faixas.Lote.model.Lote;
public record LoteDTOResponse(
    Long id,

  GuitarraDTOResponse guitarra,

   LocalDate data,

   String codigo,

 Integer estoque
) {
 public static LoteDTOResponse valueOf(Lote lote){
        return new LoteDTOResponse(lote.getId(), 
                                     GuitarraDTOResponse.valueOf(lote.getGuitarra()),
                                       lote.getData(),
                                       lote.getCodigo(),
                                       lote.getEstoque()

                                      );
    }
}
