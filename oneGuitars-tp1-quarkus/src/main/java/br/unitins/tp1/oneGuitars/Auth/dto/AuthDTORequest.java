package br.unitins.tp1.oneGuitars.Auth.dto;

import java.util.List;

import br.unitins.tp1.oneGuitars.Conta.model.Perfil;

public record AuthDTORequest(String username, String senha, List<Perfil> perfis)
 {}
  