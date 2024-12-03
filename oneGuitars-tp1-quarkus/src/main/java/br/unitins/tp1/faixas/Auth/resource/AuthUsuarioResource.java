package br.unitins.tp1.faixas.Auth.resource;
import java.util.List;

import br.unitins.tp1.faixas.Auth.dto.AuthDTORequest;
import br.unitins.tp1.faixas.Conta.dto.ContaDTOResponse;
import br.unitins.tp1.faixas.Conta.model.Perfil;
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
    public UsuarioService usuarioService;

    @Inject
    public HashService hashService;

    @Inject
    JwtService jwtService;

    @POST
    public Response login(AuthDTORequest dto){

        String hashSenha = hashService.getHashSenha(dto.senha());
        ContaDTOResponse usuario = usuarioService.login(dto.username(), hashSenha);

        if(usuario==null){
            return Response.status(Status.NOT_FOUND).entity("Usuário ou senha inválidos").build();
        }

        List<Perfil> perfisDoUsuario = usuario.perfis();
        if(perfisDoUsuario == null || perfisDoUsuario.isEmpty()){
            return Response.status(Status.FORBIDDEN)
                            .entity("Usuáio não possui perfis atribuídos")
                            .build();
        }

        boolean temPerfil = perfisDoUsuario.stream().anyMatch(perfil -> dto.perfis().contains(perfil));

        if(!temPerfil){
            return Response.status(Status.FORBIDDEN)
                            .entity("Usuário não possui os perfis necessários")
                            .build();
        }

        String token = jwtService.generateJwt(dto, usuario);

        return Response.ok(usuario)
                        .header("Authorization", token)
                        .status(Status.OK)
                        .build();
    }
}