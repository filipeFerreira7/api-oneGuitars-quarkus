package br.unitins.tp1.oneGuitars.Lote.resource;

import java.util.List;

import br.unitins.tp1.oneGuitars.Lote.dto.LoteDTORequest;
import br.unitins.tp1.oneGuitars.Lote.dto.LoteDTOResponse;
import br.unitins.tp1.oneGuitars.Lote.model.Lote;
import br.unitins.tp1.oneGuitars.Lote.service.LoteService;
import jakarta.annotation.security.RolesAllowed;
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
    @RolesAllowed({"Adm"})
    public Response findById(@PathParam("id") Long id){
      
        return Response.ok(LoteDTOResponse.valueOf(loteService.findById(id))).build();
    }

    @GET
    @Path("/search/{codigo}")
    @RolesAllowed({"Adm"})
        public Response findByCodigo(@QueryParam("codigo")String codigo){
            return Response.ok(LoteDTOResponse.valueOf(loteService.findByCodigo(codigo))).build();
        }
    

     @POST
     @RolesAllowed("Adm")
    public Response create(@Valid LoteDTORequest dto){
        Lote lote = loteService.create(dto);
        return Response.status(Status.CREATED).entity(LoteDTOResponse.valueOf(lote)).build();
    }

    @PUT
    @RolesAllowed("Adm")
    @Path("/{id}")
    public Response update(@Valid @PathParam("id") Long id , LoteDTORequest dto){
         loteService.update(id, dto);
       return Response.noContent().build();
    }
    @DELETE
    @RolesAllowed("Adm")
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        loteService.delete(id);
        return Response.noContent().build();
    }
}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)