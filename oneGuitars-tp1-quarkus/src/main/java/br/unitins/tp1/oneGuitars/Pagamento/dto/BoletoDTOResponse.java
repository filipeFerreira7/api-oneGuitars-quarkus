package br.unitins.tp1.oneGuitars.Pagamento.dto;

import java.time.LocalDate;

import br.unitins.tp1.oneGuitars.Pagamento.model.Boleto;

public record BoletoDTOResponse(
    Long id,
    String codigo,
    Double valor,
    LocalDate dataFabricacao,
    LocalDate dataValidade
) {
  public static BoletoDTOResponse valueOf(Boleto boleto){
    return new BoletoDTOResponse(boleto.getId(),
                                boleto.getCodigo(),
                                boleto.getValor(),
                                boleto.getDataFabricacao(),
                                boleto.getDataValidade());

  }
}
