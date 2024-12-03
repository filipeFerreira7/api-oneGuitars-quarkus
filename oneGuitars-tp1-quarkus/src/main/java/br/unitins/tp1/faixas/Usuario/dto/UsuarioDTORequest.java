package br.unitins.tp1.faixas.Usuario.dto;

import java.util.List;

import br.unitins.tp1.faixas.Endereco.dto.EnderecoDTORequest;
import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTORequest;
import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDTORequest(

                @NotBlank(message = "O campo nome deve ser preenchido") String nome,

                @NotNull @Min(1)
                Integer idSexo,

                @NotNull TelefoneDTORequest telefone,

                @NotNull int diaNasc,
                @NotNull int mesNasc,
                @NotNull int anoNasc,
                
                List<EnderecoDTORequest> listaEnderecos,
                
                List<Integer> idPerfis,

                @NotBlank (message = "O campo cpf deve ser preenchido")
                String cpf,

                @NotBlank (message = "O campo username deve ser preenchido")
                @Size(min = 6, max = 60, message = "O campo de username deve ter no minimo 6 caracteres e no máximo 60 ")
                 String username,

                @NotBlank(message = "O campo senha deve ser preenchido")
                @Size(min = 6, max = 60, message = "O campo de senha deve ter no minimo 6 caracteres e no máximo 60 ")
                 String senha

) {
}
