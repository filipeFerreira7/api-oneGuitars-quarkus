package br.unitins.tp1.faixas.Endereco.dto;

import br.unitins.tp1.faixas.Endereco.model.Endereco;

public record EnderecoDTOResponse(
        Long id,
        String logradouro,
        String bairro,
        String cep,
        String nomeCidade) {
    public static EnderecoDTOResponse valueOf(Endereco endereco) {
        return new EnderecoDTOResponse(endereco.getId(),
                                            endereco.getLogradouro(),
                                            endereco.getBairro(), endereco.getCep(),
                                            endereco.getCidade().getNome());
    }
}
