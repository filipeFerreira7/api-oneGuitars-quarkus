package br.unitins.tp1.faixas.Pagamento.dto;

import java.time.LocalDate;

import br.unitins.tp1.faixas.Pagamento.model.BandeiraCartao;
import br.unitins.tp1.faixas.Pagamento.model.CartaoCredito;

public record CartaoCreditoDTOResponse(
    Long id,
    String nameOwner,
    String number,
    String cvv,
    LocalDate validade,
    Double value,
    String cpf,
    BandeiraCartao bandeira
) {
  public static CartaoCreditoDTOResponse valueOf(CartaoCredito cartao){
    return new CartaoCreditoDTOResponse(cartao.getId(),
     cartao.getNameOwner(), cartao.getNumber(),
      cartao.getCvv(), cartao.getValidade(), cartao.getValue(),cartao.getCpf(),
        cartao.getBandeiraCartao());
  }
}
