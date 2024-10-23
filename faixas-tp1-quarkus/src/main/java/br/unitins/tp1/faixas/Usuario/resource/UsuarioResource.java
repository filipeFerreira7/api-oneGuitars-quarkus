package br.unitins.tp1.faixas.Usuario.resource;

import java.util.List;

import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTORequest;
import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTOResponse;
import br.unitins.tp1.faixas.Usuario.model.Usuario;
import br.unitins.tp1.faixas.Usuario.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;


@Path("/usuarios")
public class UsuarioResource {
    
    @Inject
    public UsuarioService usuarioService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
         Usuario usuario = usuarioService.findById(id);
        if(usuario ==null){
            return Response.status(Status.NOT_FOUND).build();
        }
        
        return Response.ok(UsuarioDTOResponse.valueOf(usuarioService.findById(id))).build();
    }

    @GET
    @Path("/{cpf}")
    public Response findByCpf(@PathParam("cpf") String cpf){
         Usuario usuarioCpf = usuarioService.findByCpf(cpf);
        if(usuarioCpf ==null){
            return Response.status(Status.NOT_FOUND).build();
        }
        
        return Response.ok(UsuarioDTOResponse.valueOf(usuarioService.findByCpf(cpf))).build();
    }

    @GET
    @Path("/search/{nome}")
    public List<UsuarioDTOResponse> findByNome(@PathParam("nome")String nome){

        return usuarioService.findByNome(nome).
                     stream().
                     map(o -> UsuarioDTOResponse.valueOf(o))
                    .toList();
    }

    @GET
    public Response findAll(){
        return Response.ok(usuarioService.
                     findAll().
                     stream().
                     map(o -> UsuarioDTOResponse.valueOf(o))
                    .toList()).build();
        
    }

    @POST
    public Response create(@Valid UsuarioDTORequest dto){
        return  Response.status(Status.CREATED).entity(
            UsuarioDTOResponse.valueOf(usuarioService.create(dto))
            ).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@Valid @PathParam("id") Long id, @Valid UsuarioDTORequest dto){
        usuarioService.update(id, dto);
       return Response.noContent().build();
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        usuarioService.delete(id);
        return Response.noContent().build();
    }
}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)