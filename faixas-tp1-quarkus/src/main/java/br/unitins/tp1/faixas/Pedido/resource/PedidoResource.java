package br.unitins.tp1.faixas.Pedido.resource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import br.unitins.tp1.faixas.Pedido.dto.PedidoDTORequest;
import br.unitins.tp1.faixas.Pedido.dto.PedidoDTOResponse;
import br.unitins.tp1.faixas.Pedido.service.PedidoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
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
    @Path("/search/{dataCompra}")
    public List<PedidoDTOResponse> findByDataCompra(@PathParam ("dataCompra") String dataCompraStr){
        // Defina o formato de data e hora que você espera na URL
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"); // ajuste conforme necessário
        
        LocalDateTime dataCompra;
        try {
            // Converta a String recebida em LocalDateTime
            dataCompra = LocalDateTime.parse(dataCompraStr, formatter);
        } catch (DateTimeParseException e) {
            throw new WebApplicationException("Formato de data e hora inválido", Response.Status.BAD_REQUEST);
        }
        
        return pedidoService
            .findByDataCompra(dataCompra)
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
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id){
        pedidoService.delete(id);
    }
}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)