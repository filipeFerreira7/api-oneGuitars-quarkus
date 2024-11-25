package br.unitins.tp1.faixas.Auth.resource;

import br.unitins.tp1.faixas.Administrador.service.AdministradorService;
import br.unitins.tp1.faixas.Auth.dto.AuthDTORequest;
import br.unitins.tp1.faixas.Conta.dto.ContaDTOResponse;
import br.unitins.tp1.faixas.Hash.service.HashService;
import br.unitins.tp1.faixas.Jwt.service.JwtService;
import br.unitins.tp1.faixas.Usuario.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/auth")
public class AuthUsuarioResource {
    
    @Inject
    public UsuarioService clienteService;

    @Inject
    public AdministradorService admService;

    @Inject
    public HashService hashService;

    @Inject
    JwtService jwtService;

    @POST
    public Response login(AuthDTORequest dto){
        String hashSenha = hashService.getHashSenha(dto.senha());

        ContaDTOResponse usuario = null;

        if(dto.perfil() == 1){
            // cliente
            usuario = clienteService.login(dto.username(), hashSenha);
        } else if (dto.perfil() == 2){
            // funcionario
            usuario = admService.login(dto.username(), hashSenha);
        } else {
            return Response.status(Status.NOT_FOUND).header("Perfil", "perfis existentes: 1-cliente | 2-funcionario").build();
        }

        if(usuario != null){
            return Response.ok(usuario).header("Authorization", jwtService.generateJwt(dto, usuario))
                            .status(Status.CREATED)
                            .build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
        
    }
}