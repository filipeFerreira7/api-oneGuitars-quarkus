package br.unitins.tp1.faixas.Telefone.resource;

import java.util.List;

import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTORequest;
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


@Path("/telefones")
public class TelefoneResource {
    
    @Inject
    public TelefoneService telefoneService;

    @GET
    @Path("/{id}")
    public Telefone findById(Long id){
        return telefoneService.findById(id);
    }

    @GET
    @Path("/search/{numero}")
    public List<Telefone> findByNumero(String numero){
        return telefoneService.findByNumero(numero);
    }

    @GET
    public List<Telefone> findAll(){
        return telefoneService.findAll();
    }

    @POST
    public Telefone create(TelefoneDTORequest telefone){

        return telefoneService.create(telefone);
    }

    @PUT
    @Path("/{id}")
    public Response update(@Valid @PathParam("id") Long id,@Valid TelefoneDTORequest dto){
        telefoneService.update(id,dto);
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