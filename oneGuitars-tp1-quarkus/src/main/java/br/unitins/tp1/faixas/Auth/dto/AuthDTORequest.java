package br.unitins.tp1.faixas.Auth.dto;

import java.util.List;

import br.unitins.tp1.faixas.Conta.model.Perfil;

public record AuthDTORequest(String username, String senha, List<Perfil> perfis)
 {}
