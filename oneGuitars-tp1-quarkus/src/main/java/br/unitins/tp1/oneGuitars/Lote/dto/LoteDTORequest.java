package br.unitins.tp1.oneGuitars.Lote.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record LoteDTORequest (
      
@NotNull(message = "O campo estoque deve ser informado")
@Min(0)
 Integer estoque,

  @NotNull(message = "A data não pode ser nula") @PastOrPresent(message = "A data deve ser hoje ou estar no passado")
   LocalDate data,

   @NotNull(message = "O campo id guitarra deve ser informado")
   @Min(1)
   Long idGuitarra,
   @NotBlank(message = "O codigo não deve ficar em branco")
    String codigo
    
)
{}
