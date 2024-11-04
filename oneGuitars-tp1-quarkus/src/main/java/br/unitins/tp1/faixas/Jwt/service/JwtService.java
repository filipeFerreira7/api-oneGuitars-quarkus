package br.unitins.tp1.faixas.Jwt.service;

import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTOResponse;

public interface JwtService {

    public String generateJwt(UsuarioDTOResponse dto);

}
