package br.unitins.tp1.faixas.Conta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDTORequest(

        @NotBlank(message = "O nome não pode ser nulo ou vazio") @Size(min = 4, max = 60, message = "O tamanho deve ser entre 4 e 60 caracteres") String username,

        @NotBlank(message = "A senha não pode ser nula ou vazia") @Size(min = 8, max = 60, message = "O tamanho deve ser entre 8 e 60 caracteres") String senha

) {
}
