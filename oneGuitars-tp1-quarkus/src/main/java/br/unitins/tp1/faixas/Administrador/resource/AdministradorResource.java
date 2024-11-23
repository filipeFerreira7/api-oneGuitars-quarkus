package br.unitins.tp1.faixas.Administrador.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.tp1.faixas.Administrador.dto.AdministradorDTORequest;
import br.unitins.tp1.faixas.Administrador.dto.AdministradorPasswordUpdateDTO;
import br.unitins.tp1.faixas.Administrador.dto.AdministradorUsernameUpdateDTO;
import br.unitins.tp1.faixas.Administrador.service.AdministradorService;
import br.unitins.tp1.faixas.Usuario.repository.UsuarioRepository;
import br.unitins.tp1.faixas.validation.ValidationException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/adms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdministradorResource {
    
    @Inject
    public AdministradorService service;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    JsonWebToken jwt;

    
    @POST
    public Response create(AdministradorDTORequest dto){
        return Response.ok(service.create(dto)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("Adm")
    public Response delete( @PathParam("id") Long id){
        if(service.delete(id))
            return Response.status(Status.NO_CONTENT).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Adm")
    public Response update( @PathParam("id") Long id, AdministradorDTORequest dto) throws jakarta.xml.bind.ValidationException{
        try {
            service.update(id, dto);
            return Response.status(Status.NO_CONTENT).build();
        } catch (ValidationException e) {
            // Retornar uma resposta de erro com o código de status apropriado e mensagem de erro
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    

    @PATCH
    @RolesAllowed("Adm")
    @Path("/update-password")
    public Response updateUsuarioPassword(AdministradorPasswordUpdateDTO passwordUpdateDTO){
        service.updateUsuarioPassword(passwordUpdateDTO);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed("Adm")
    @Path("/update-username")
    public Response updateUsuarioUsername(AdministradorUsernameUpdateDTO usernameUpdateDTO){
        service.updateUsuarioUsername(usernameUpdateDTO);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @RolesAllowed("Adm")
    public Response findAll(){
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/search/id/{id}")
    @RolesAllowed("Adm")
    public Response findById( @PathParam("id") Long id){
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    @RolesAllowed("Adm")
    public Response findByNome( @PathParam("nome") String nome){
        return Response.ok(service.findByNome(nome)).build();
    }

    @GET
    @Path("/search/cpf/{cpf}")
    @RolesAllowed("Adm")
    public Response findByCpf( @PathParam("cpf") String cpf){
        return Response.ok(service.findByCpf(cpf)).build();
    }

    @GET
    @Path("/search/username/{username}")
    @RolesAllowed("Adm")
    public Response findByUsername( @PathParam("username") String username){
        return Response.ok(service.findByUsername(username)).build();
    }
}