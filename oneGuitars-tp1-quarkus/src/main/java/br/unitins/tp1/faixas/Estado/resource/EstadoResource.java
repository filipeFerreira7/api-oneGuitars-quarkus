package br.unitins.tp1.faixas.Estado.resource;

import java.util.List;

import br.unitins.tp1.faixas.Estado.dto.EstadoDTORequest;
import br.unitins.tp1.faixas.Estado.dto.EstadoDTOResponse;
import br.unitins.tp1.faixas.Estado.service.EstadoService;
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
    public Response findById(@PathParam("id") Long id){
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
    public Response create(@Valid EstadoDTORequest estado){
        return Response.status(Status.CREATED)
        .entity(EstadoDTOResponse.valueOf(estadoService.create(estado))).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@Valid @PathParam("id") Long id,@Valid EstadoDTORequest dto){
        estadoService.update(id,dto);
        return Response.noContent().build();
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        estadoService.delete(id);
        return Response.noContent().build();
    }
}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)