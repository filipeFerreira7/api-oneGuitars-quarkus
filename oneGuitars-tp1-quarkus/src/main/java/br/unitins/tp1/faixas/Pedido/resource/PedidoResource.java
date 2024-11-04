package br.unitins.tp1.faixas.Pedido.resource;

import java.util.List;

import br.unitins.tp1.faixas.Pedido.dto.PedidoDTORequest;
import br.unitins.tp1.faixas.Pedido.dto.PedidoDTOResponse;
import br.unitins.tp1.faixas.Pedido.service.PedidoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
// controlador
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/pedidos")
public class PedidoResource {
    
    @Inject
    public PedidoService pedidoService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
      
        return Response.ok(PedidoDTOResponse.valueOf(pedidoService.findById(id))).build();
    }

    @GET
    @Path("/search/{username}")
    public List<PedidoDTOResponse> findByUsername(@PathParam ("username") String username){
        
        return pedidoService
            .findByUsername(username)
            .stream()
            .map(o -> PedidoDTOResponse.valueOf(o))
            .toList();
    }
    
     @POST
    public Response create(@Valid PedidoDTORequest dto){
        return  Response.status(Status.CREATED).entity(
            PedidoDTOResponse.valueOf(pedidoService.create(dto))
            ).build();
    }
}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)