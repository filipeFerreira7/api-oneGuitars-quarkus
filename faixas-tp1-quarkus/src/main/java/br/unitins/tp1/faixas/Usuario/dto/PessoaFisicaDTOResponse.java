package br.unitins.tp1.faixas.Usuario.dto;

import br.unitins.tp1.faixas.Usuario.model.PessoaFisica;
import br.unitins.tp1.faixas.Usuario.model.Sexo;

public record PessoaFisicaDTOResponse(
    Long id,
    String nome,
    String cpf,
    Sexo sexo
) {
    public static PessoaFisicaDTOResponse valueOf(PessoaFisica pessoafisica) {
        return new PessoaFisicaDTOResponse(
            pessoafisica.getId(),
            pessoafisica.getNome(),
            pessoafisica.getCpf(),
            pessoafisica.getSexo()
        );
    }
}
