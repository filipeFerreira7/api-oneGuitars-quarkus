package br.unitins.tp1.oneGuitars.PessoaFisica.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PessoaFisicaDTORequest(
        @NotBlank(message = "O campo nome deve ser preenchido") String nome,
        @NotBlank(message = "O campo CPF deve ser preenchido") String cpf,
        @NotNull() Long idUsuario,
        @NotBlank(message = "O campo email deve ser preenchido") @Size(max = 100) String email,

        @NotNull @Min(1) Integer idSexo

) {
}
