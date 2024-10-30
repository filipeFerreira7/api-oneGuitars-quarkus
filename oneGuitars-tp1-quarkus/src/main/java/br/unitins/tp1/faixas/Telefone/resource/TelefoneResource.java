package br.unitins.tp1.faixas.Telefone.resource;

import java.util.List;

import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTORequest;
import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTOResponse;
import br.unitins.tp1.faixas.Telefone.model.Telefone;
import br.unitins.tp1.faixas.Telefone.service.TelefoneService;
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


@Path("/telefones")
public class TelefoneResource {
    
    @Inject
    public TelefoneService telefoneService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id")Long id) {
        return Response.ok(TelefoneDTOResponse.valueOf(telefoneService.findById(id))).build();
    }
    @GET
    @Path("/search/{nome}")
    public List<TelefoneDTOResponse> findByNumero(@PathParam("numero") String numero){
        return telefoneService.findByNumero(numero).stream()
        .map(o -> TelefoneDTOResponse.valueOf(o))
        .toList();
    }

    @GET
    public List<Telefone> findAll(){
        return telefoneService.findAll();
    }

     @POST
    public Response create(@Valid TelefoneDTORequest dto){
        return  Response.status(Status.CREATED).entity(
            TelefoneDTOResponse.valueOf(telefoneService.create(dto))
            ).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@Valid @PathParam("id") Long id ,@Valid TelefoneDTORequest dto){
         telefoneService.update(id, dto);
       return Response.noContent().build();
    }
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id){
        telefoneService.delete(id);
    }
}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)