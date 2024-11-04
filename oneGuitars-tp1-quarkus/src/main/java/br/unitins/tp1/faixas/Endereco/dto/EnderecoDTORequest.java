package br.unitins.tp1.faixas.Endereco.dto;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record EnderecoDTORequest (

@NotBlank(message = "O logradouro não pode estar em branco")
 String logradouro,

@NotBlank(message = "O bairro não pode estar em brannco")
String bairro,

@NotBlank(message = "O cep não pode estar em branco")
String cep,

@NotNull()
@Min(1)
Long idCidade



)  
{}
