package br.unitins.tp1.faixas.resource;

import java.util.List;


import br.unitins.tp1.faixas.DTO.GuitarraDTORequest;
import br.unitins.tp1.faixas.DTO.GuitarraDTOResponse;
import br.unitins.tp1.faixas.model.Guitarra;
import br.unitins.tp1.faixas.service.GuitarraService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

// controlador
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/guitarras")
public class GuitarraResource {
    
    @Inject
    public GuitarraService guitarraService;

    @GET
    @Path("/{id}")
    public Guitarra findById(Long id){
        return guitarraService.findById(id);
    }

    @GET
    @Path("/search/{nome}")
    public List<Guitarra> findByNome(String nome){
        return guitarraService.findByNome(nome);
    }

    @GET
    public List<Guitarra> findAll(){
        return guitarraService.findAll();
    }

     @POST
    public Response create(@Valid GuitarraDTORequest dto){
        return  Response.status(Status.CREATED).entity(
            GuitarraDTOResponse.valueOf(guitarraService.create(dto))
            ).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@Valid @PathParam("id") Long id ,@Valid GuitarraDTORequest dto){
         guitarraService.update(id, dto);
       return Response.noContent().build();
    }
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id){
        guitarraService.delete(id);
    }
}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)