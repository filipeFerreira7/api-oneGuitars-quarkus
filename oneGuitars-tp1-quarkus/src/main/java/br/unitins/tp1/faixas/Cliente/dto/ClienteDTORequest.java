package br.unitins.tp1.faixas.Cliente.dto;

import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTORequest;
import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteDTORequest(

                @NotBlank(message = "O campo nome deve ser preenchido") String nome,

                @NotNull @Size(min = 1, max = 2, message = "escolha as opções disponíveis") Integer idSexo,

                @NotNull TelefoneDTORequest telefone,

                @NotNull int diaNasc,
                @NotNull int mesNasc,
                @NotNull int anoNasc,
                
                @NotNull @Min(1) Integer idPerfil,

                @NotBlank (message = "O campo cpf deve ser preenchido")
                String cpf,

                @NotBlank (message = "O campo username deve ser preenchido")
                 String username,

                @NotBlank(message = "O campo senha deve ser preenchido")
                 String senha

) {
}
