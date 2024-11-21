package br.unitins.tp1.faixas.Pagamento.dto;

import java.time.LocalDate;

import br.unitins.tp1.faixas.Pagamento.model.BandeiraCartao;
import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CartaoCreditoDTORequest (
    @NotBlank(message = "The field nameOwner must be filled")
    @Size(max=60)
    String nameOwner,
    @NotBlank(message = "The field number is required")
    String number,
    @NotBlank(message = "The field cvv is required")
    String cvv,
    @NotBlank(message = "The field validade is required")
    LocalDate validade,
    @NotBlank(message = "The field cpf is required")
    String cpf,
    @NotNull()
    BandeiraCartao bandeira

) {
    
}
