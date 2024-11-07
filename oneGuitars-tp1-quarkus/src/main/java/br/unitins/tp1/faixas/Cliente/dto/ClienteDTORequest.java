package br.unitins.tp1.faixas.Cliente.dto;

import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTORequest;
import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ClienteDTORequest(

        @NotBlank
         String nome,
         
        @NotNull
        @Min(1)
       Integer idSexo,

        @NotNull
        TelefoneDTORequest telefone,

        int diaNasc,
        int mesNasc,
        int anoNasc,
        @NotNull
        @Min(1)
        Integer idPerfil,

        @NotBlank
        String cpf,

        @NotBlank String username,

        @NotBlank String senha

) {
}
