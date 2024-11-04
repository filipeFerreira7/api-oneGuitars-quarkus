package br.unitins.tp1.faixas.Cliente.resource;

import br.unitins.tp1.faixas.Cliente.dto.ClienteDTORequest;
import br.unitins.tp1.faixas.Cliente.service.ClienteService;
import br.unitins.tp1.faixas.Usuario.repository.UsuarioRepository;
import br.unitins.tp1.faixas.Usuario.service.UsuarioService;
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
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {
    
    @Inject
     ClienteService clienteService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    UsuarioRepository usuarioRepository;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
            return Response.ok(clienteService.findById(id)).build();   
    }

    @GET
    @Path("/search/cpf/{cpf}")
    public Response findByCpf(@PathParam("cpf") String cpf){
       return Response.ok(clienteService.findByCpf(cpf)).build();
    }

    @GET
    @Path("/search/{nome}")
    public Response findByNome(@PathParam("nome")String nome){
        return Response.ok(clienteService.findByNome(nome)).build();
    }

    @GET
    public Response findAll(){
     return Response.ok(clienteService.findAll()).build();
        
    }

    @POST
    public Response create(@Valid ClienteDTORequest dto) {
        clienteService.create(dto);
        return Response.status(Response.Status.CREATED).entity(dto).build();
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