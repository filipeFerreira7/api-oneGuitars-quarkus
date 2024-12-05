package br.unitins.tp1.oneGuitars.Cidade.resource;

import java.util.List;

import br.unitins.tp1.oneGuitars.Cidade.dto.CidadeDTORequest;
import br.unitins.tp1.oneGuitars.Cidade.dto.CidadeDTOResponse;
import br.unitins.tp1.oneGuitars.Cidade.service.CidadeService;
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


@Path("/cidades")
public class CidadeResource {
    
    @Inject
    public CidadeService cidadeService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"Adm","User"})
    public Response findById(@PathParam("id") Long id){
        
        return Response.ok(CidadeDTOResponse.valueOf(cidadeService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public List<CidadeDTOResponse> findByNome(@PathParam("nome")String nome){
        return cidadeService.findByNome(nome).
                     stream().
                     map(o -> CidadeDTOResponse.valueOf(o))
                    .toList();
    }

    @GET
    public Response findAll(){
        return Response.ok(cidadeService.
                     findAll().
                     stream().
                     map(o -> CidadeDTOResponse.valueOf(o))
                    .toList()).build();
        
    }

    @POST
    @RolesAllowed("Adm")
    public Response create(@Valid CidadeDTORequest dto){
        return  Response.status(Status.CREATED).entity(
            CidadeDTOResponse.valueOf(cidadeService.create(dto))
            ).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Adm")
    public Response update(@Valid @PathParam("id") Long id, @Valid CidadeDTORequest dto){
        cidadeService.update(id, dto);
       return Response.noContent().build();
    }
    @DELETE
    @Path("/{id}")
    @RolesAllowed("Adm")
    public Response delete(@PathParam("id") Long id){
        cidadeService.delete(id);
        return Response.noContent().build();
    }
}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)