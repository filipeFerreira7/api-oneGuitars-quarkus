package br.unitins.tp1.faixas.Usuario.resource;

import java.util.List;

import br.unitins.tp1.faixas.Usuario.dto.ClienteDTORequest;
import br.unitins.tp1.faixas.Usuario.dto.ClienteDTOResponse;
import br.unitins.tp1.faixas.Usuario.service.ClienteService;
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


@Path("/clientes")
public class ClienteResource {
    
    @Inject
    public ClienteService clienteService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        
        return Response.ok(ClienteDTOResponse.valueOf(clienteService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public List<ClienteDTOResponse> findByNome(@PathParam("nome")String nome){
        return clienteService.findByNome(nome).
                     stream().
                     map(o -> ClienteDTOResponse.valueOf(o))
                    .toList();
    }

    @GET
    public Response findAll(){
        return Response.ok(clienteService.
                     findAll().
                     stream().
                     map(o -> ClienteDTOResponse.valueOf(o))
                    .toList()).build();
        
    }

    @POST
    public Response create(@Valid ClienteDTORequest dto){
        return  Response.status(Status.CREATED).entity(
            ClienteDTOResponse.valueOf(clienteService.create(dto))
            ).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@Valid @PathParam("id") Long id, @Valid ClienteDTORequest dto){
        clienteService.update(id, dto);
       return Response.noContent().build();
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        clienteService.delete(id);
        return Response.noContent().build();
    }
}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)