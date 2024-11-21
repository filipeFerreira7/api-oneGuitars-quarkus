package br.unitins.tp1.faixas.Pagamento.dto;

import java.time.LocalDate;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;

public record BoletoDTORequest(
        @NotBlank(message = "O campo nome deve ser preenchido")
        String codigo,
        @NotBlank(message = "O campo cpf deve ser preenchido")
        String cpf,
        @NotNull
        LocalDate dataFabricacao,
        @NotNull
         LocalDate dataValidade) {

}
