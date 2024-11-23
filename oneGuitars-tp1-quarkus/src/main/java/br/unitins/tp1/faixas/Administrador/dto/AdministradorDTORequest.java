package br.unitins.tp1.faixas.Administrador.dto;

import br.unitins.tp1.faixas.Telefone.model.Telefone;
import jakarta.validation.constraints.NotBlank;

public record AdministradorDTORequest(
    // Parte Pessoa
    @NotBlank
    String nome,
    Telefone telefone,
    int diaNasc,
    int mesNasc,
    int anoNasc,

    // Parte PessoaFisica
    @NotBlank
    String cpf,
    
    @NotBlank
    String username,

    @NotBlank
    String senha,

    // Parte Funcionario
    String codigoContrato,
    int diaAdmissao,
    int mesAdmissao,
    int anoAdmissao
) {}