package br.unitins.tp1.faixas.Usuario.dto;

import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTORequest;
import io.smallrye.common.constraint.NotNull;

public record UsuarioDTORequest(

    @NotNull
    TelefoneDTORequest telefoneDTO,

    @NotNull
    PessoaFisicaDTORequest pessoaFisicaDto
)
{
}
