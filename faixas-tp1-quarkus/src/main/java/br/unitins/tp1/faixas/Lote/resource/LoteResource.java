package br.unitins.tp1.faixas.Lote.resource;

import java.util.List;

import br.unitins.tp1.faixas.Lote.dto.LoteDTORequest;
import br.unitins.tp1.faixas.Lote.dto.LoteDTOResponse;
import br.unitins.tp1.faixas.Lote.model.Lote;
import br.unitins.tp1.faixas.Lote.service.LoteService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
// controlador
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/lotes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class LoteResource {
    
    @Inject
    public LoteService loteService;

    @GET
    public Response findAll(){
        List<Lote> lotes = loteService.findAll();
        return Response.ok(lotes.stream().map(LoteDTOResponse::valueOf)).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
      
        return Response.ok(LoteDTOResponse.valueOf(loteService.findById(id))).build();
    }

    @GET
    @Path("/search/{codigo}")
        public Response findByCodigo(@QueryParam("codigo")String codigo){
            List<Lote> lotes = loteService.findByCodigo(codigo);
            return Response.ok(lotes.stream().map(LoteDTOResponse::valueOf).toList()).build();
        }
    

     @POST
    public Response create(@Valid LoteDTORequest dto){
        Lote lote = loteService.create(dto);
        return Response.status(Status.CREATED).entity(LoteDTOResponse.valueOf(lote)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@Valid @PathParam("id") Long id , LoteDTORequest dto){
         loteService.update(id, dto);
       return Response.noContent().build();
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        loteService.delete(id);
        return Response.noContent().build();
    }
}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)