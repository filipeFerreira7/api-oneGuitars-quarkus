package br.unitins.tp1.faixas.Guitarra.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GuitarraDTORequest(
     @NotBlank(message= "O nome da guitarra deve ser informado")
    @Size(max=60)
    String nome,
    @NotNull (message= "o numero de série deve ser informado")
    String numeroSerie,
    @NotBlank(message="A cor da guitarra deve ser informada")
    String cor,
    @NotNull(message = "O preço da guitarra deve ser informado")
    @Min(1)
    Double preco,
    @NotNull (message = "a especificação deve ser informada")
    @Min(1)
    Long idEspecificacao,
    @NotNull(message = "a marca deve ser informada")
    @Min(1)
    Long idMarca
    
){

}
