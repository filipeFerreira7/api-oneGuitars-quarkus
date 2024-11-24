package br.unitins.tp1.faixas.Administrador.dto;

import br.unitins.tp1.faixas.Telefone.model.Telefone;
import jakarta.validation.constraints.NotBlank;

public record AdministradorDTORequest(
   
    @NotBlank
    String nome,
    Telefone telefone,
    int diaNasc,
    int mesNasc,
    int anoNasc,

    @NotBlank
    String cpf,
    
    @NotBlank
    String username,

    @NotBlank
    String senha,

    String codigoAdm
 
) {}