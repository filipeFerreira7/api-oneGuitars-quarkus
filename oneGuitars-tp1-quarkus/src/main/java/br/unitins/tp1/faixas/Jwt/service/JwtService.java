package br.unitins.tp1.faixas.Jwt.service;

import br.unitins.tp1.faixas.Auth.dto.AuthDTORequest;
import br.unitins.tp1.faixas.Conta.dto.ContaDTOResponse;

public interface JwtService {

    public String generateJwt(AuthDTORequest authDTO,ContaDTOResponse dto);

}
