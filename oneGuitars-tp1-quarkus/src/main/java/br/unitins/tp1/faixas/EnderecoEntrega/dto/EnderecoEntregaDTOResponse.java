package br.unitins.tp1.faixas.EnderecoEntrega.dto;

import br.unitins.tp1.faixas.EnderecoEntrega.model.EnderecoEntrega;

public record EnderecoEntregaDTOResponse(
        Long id,
        String logradouro,
        String bairro,
        String cep,
        String nomeCidade) {
    public static EnderecoEntregaDTOResponse valueOf(EnderecoEntrega enderecoEntrega) {
        return new EnderecoEntregaDTOResponse(enderecoEntrega.getId(),
                                            enderecoEntrega.getLogradouro(),
                                            enderecoEntrega.getBairro(), enderecoEntrega.getCep(),
                                            enderecoEntrega.getCidade().getNome());
    }
}
