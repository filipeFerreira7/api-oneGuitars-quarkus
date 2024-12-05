package br.unitins.tp1.oneGuitars.Estado.resource;

import java.util.List;

import br.unitins.tp1.oneGuitars.Estado.dto.EstadoDTORequest;
import br.unitins.tp1.oneGuitars.Estado.dto.EstadoDTOResponse;
import br.unitins.tp1.oneGuitars.Estado.service.EstadoService;
import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
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


@Path("/estados")
public class EstadoResource {
    
    @Inject
    public EstadoService estadoService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"Adm", "User"})
    public Response findById(@PathParam("id") Long id){
        Log.info("Procurando estado pelo id: "+ id);
        return Response.ok(EstadoDTOResponse.valueOf(estadoService.findById(id))).build();
    }

      @GET
    @Path("/search/{nome}")
    public List<EstadoDTOResponse> findByNome(@PathParam("nome")String nome){

        return estadoService.findByNome(nome).
                     stream().
                     map(o -> EstadoDTOResponse.valueOf(o))
                    .toList();
    }

    @GET
    public Response findAll(){
        return Response.ok(estadoService.findAll().stream().map(o -> EstadoDTOResponse.valueOf(o)).toList()).build();
    }

    @POST
    @RolesAllowed("Adm")
    public Response create(@Valid EstadoDTORequest estado){
        return Response.status(Status.CREATED)
        .entity(EstadoDTOResponse.valueOf(estadoService.create(estado))).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Adm")
    public Response update(@Valid @PathParam("id") Long id,@Valid EstadoDTORequest dto){
        estadoService.update(id,dto);
        return Response.noContent().build();
    }
    @DELETE
    @Path("/{id}")
    @RolesAllowed("Adm")
    public Response delete(@PathParam("id") Long id){
        estadoService.delete(id);
        return Response.noContent().build();
    }
}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)