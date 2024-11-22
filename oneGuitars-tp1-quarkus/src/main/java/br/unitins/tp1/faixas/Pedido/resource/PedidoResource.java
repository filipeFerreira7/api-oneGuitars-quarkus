package br.unitins.tp1.faixas.Pedido.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.tp1.faixas.Pagamento.dto.BoletoDTORequest;
import br.unitins.tp1.faixas.Pagamento.dto.CartaoCreditoDTORequest;
import br.unitins.tp1.faixas.Pedido.dto.PedidoDTORequest;
import br.unitins.tp1.faixas.Pedido.dto.PedidoDTOResponse;
import br.unitins.tp1.faixas.Pedido.service.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
// controlador
import jakarta.ws.rs.core.Response;

@Path("/pedidos")
public class PedidoResource {
    
    @Inject
    public PedidoService pedidoService;

    @Inject
    public JsonWebToken jsonWebToken;
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
     @RolesAllowed("User")
    public Response create(@Valid PedidoDTORequest dto){
        return Response.ok(pedidoService.create(dto)).build();
    }

      @POST
    @Path("/{id}/pagamento-cartao")
    @RolesAllowed("User")
    public Response pagamentoCartao(@PathParam("id") Long idPedido, @Valid CartaoCreditoDTORequest cartaoDTO) {
        pedidoService.pagamentoCartao(idPedido, cartaoDTO);
        return Response.ok().build();
    }

    // **Endpoint para pagamento via Pix**
    @POST
    @Path("/{id}/pagamento-")
    @RolesAllowed("User")
    public Response pagamentoBoleto(@PathParam("id") Long idPedido, @Valid Long idBoleto) {
        pedidoService.pagamentoBoleto(idPedido, idBoleto);
        return Response.ok().build();
    }

    
}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)