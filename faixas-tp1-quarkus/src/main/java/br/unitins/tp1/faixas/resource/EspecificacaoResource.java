package br.unitins.tp1.faixas.resource;

import java.util.List;

import br.unitins.tp1.faixas.DTO.EspecificacaoDTORequest;
import br.unitins.tp1.faixas.DTO.EspecificacaoDTOResponse;
import br.unitins.tp1.faixas.service.EspecificacaoService;
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


@Path("/especificacoes")
public class EspecificacaoResource {
    
    @Inject
    public EspecificacaoService especificacaoService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        
        return Response.ok(EspecificacaoDTOResponse.valueOf(especificacaoService.findById(id))).build();
    }

    @GET
    @Path("/search/{sku}")
    public List<EspecificacaoDTOResponse> findBySku(@PathParam("sku")String sku){
        return especificacaoService.findBySku(sku).
                     stream().
                     map(o -> EspecificacaoDTOResponse.valueOf(o))
                    .toList();
    }

    @GET
    public Response findAll(){
        return Response.ok(especificacaoService.
                     findAll().
                     stream().
                     map(o -> EspecificacaoDTOResponse.valueOf(o))
                    .toList()).build();
        
    }

    @POST
    public Response create(@Valid EspecificacaoDTORequest dto){
        return  Response.status(Status.CREATED).entity(
            EspecificacaoDTOResponse.valueOf(especificacaoService.create(dto))
            ).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@Valid @PathParam("id") Long id, @Valid EspecificacaoDTORequest dto){
        especificacaoService.update(id, dto);
       return Response.noContent().build();
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        especificacaoService.delete(id);
        return Response.noContent().build();
    }
}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)