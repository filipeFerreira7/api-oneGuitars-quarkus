package br.unitins.tp1.faixas.Administrador.dto;

import java.time.LocalDate;

import br.unitins.tp1.faixas.Administrador.model.Administrador;

public record AdministradorDTOResponse(
        Long id,
        String nome,
        String dd,
        String telefone,
        LocalDate dataNascimento,
        String cpf,
        String codigoAdm,
        String username,
        String senha) {
    public static AdministradorDTOResponse valueof(Administrador administrador) {

        return new AdministradorDTOResponse(administrador.getId(),
                administrador.getPessoaFisica().getNome(),
                administrador.getPessoaFisica().getTelefone().getDd(),
                administrador.getPessoaFisica().getTelefone().getNumero(),
                administrador.getPessoaFisica().getDataNascimento(),
                administrador.getPessoaFisica().getCpf(),
                administrador.getCodigoAdm(),
                administrador.getPessoaFisica().getUsuario().getUsername(),
                administrador.getPessoaFisica().getUsuario().getSenha()
        );

               
    }
}