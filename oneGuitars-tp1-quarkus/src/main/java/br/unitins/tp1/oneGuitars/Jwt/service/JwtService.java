package br.unitins.tp1.oneGuitars.Jwt.service;

import br.unitins.tp1.oneGuitars.Auth.dto.AuthDTORequest;
import br.unitins.tp1.oneGuitars.Conta.dto.ContaDTOResponse;

public interface JwtService {

    public String generateJwt(AuthDTORequest authDTO,ContaDTOResponse dto);

}
