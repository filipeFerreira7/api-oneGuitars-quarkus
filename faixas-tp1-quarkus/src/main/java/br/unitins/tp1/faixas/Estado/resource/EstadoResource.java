package br.unitins.tp1.faixas.Estado.resource;

import java.util.List;

import br.unitins.tp1.faixas.Estado.dto.EstadoDTORequest;
import br.unitins.tp1.faixas.Estado.model.Estado;
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


@Path("/estados")
public class EstadoResource {
    
    @Inject
    public EstadoService estadoService;

    @GET
    @Path("/{id}")
    public Estado findById(Long id){
        return estadoService.findById(id);
    }

    @GET
    @Path("/search/{nome}")
    public List<Estado> findByNome(String nome){
        return estadoService.findByNome(nome);
    }

    @GET
    public List<Estado> findAll(){
        return estadoService.findAll();
    }

    @POST
    public Estado create(EstadoDTORequest estado){

        return estadoService.create(estado);
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