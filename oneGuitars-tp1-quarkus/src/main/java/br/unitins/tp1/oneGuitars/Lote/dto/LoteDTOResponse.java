package br.unitins.tp1.oneGuitars.Lote.dto;

import java.time.LocalDate;

import br.unitins.tp1.oneGuitars.Lote.model.Lote;
public record LoteDTOResponse(
    Long id,

 String nomeGuitarra,

   LocalDate data,

   String codigo,

 Integer estoque
) {
 public static LoteDTOResponse valueOf(Lote lote){
        return new LoteDTOResponse(lote.getId(), 
                                      lote.getGuitarra().getNome(),
                                       lote.getData(),
                                       lote.getCodigo(),
                                       lote.getEstoque()

                                      );
    }
}
