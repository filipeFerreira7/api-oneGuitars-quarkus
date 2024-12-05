package br.unitins.tp1.oneGuitars.Estado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstadoDTORequest(
    @NotBlank(message = "o nome do estado precisa ser preenchido")
    @Size(max=60, message = "O nome deve possuir no máximo 60 caracteres")
    String nome,
    @NotBlank(message="o campo sigla é obrigatório")
    @Size(min = 2, max = 2, message = "A sigla deve possuir dois caracteres")
    String sigla
) {
     
}
