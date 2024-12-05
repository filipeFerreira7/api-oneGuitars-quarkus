package br.unitins.tp1.faixas.Pedido.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.tp1.faixas.Pagamento.dto.CartaoCreditoDTORequest;
import br.unitins.tp1.faixas.Pedido.dto.PedidoDTORequest;
import br.unitins.tp1.faixas.Pedido.dto.PedidoDTOResponse;
import br.unitins.tp1.faixas.Pedido.dto.StatusPedidoDTORequest;
import br.unitins.tp1.faixas.Pedido.service.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
// controlador
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/pedidos")
public class PedidoResource {
    private static final Logger LOG = Logger.getLogger(PedidoResource.class);
    
    @Inject
    public PedidoService pedidoService;

    @Inject
    public JsonWebToken jsonWebToken;
    @GET
    @Path("/{id}")
    @RolesAllowed("Adm")
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

      @PATCH
    @Path("/{id}/pagamento-cartao")
    @RolesAllowed("User")
    public Response pagamentoCartao(@PathParam("id") Long id, @Valid CartaoCreditoDTORequest cartaoDTO) {
        pedidoService.pagamentoCartao(id, cartaoDTO);
        return Response.status(Status.NO_CONTENT).build();
    }

    @POST
    @Path("/{id}/pagamento/info/boleto")
    @RolesAllowed("User")
    public Response gerarInfoBoleto(@PathParam("id") Long id){
        return Response.status(201).entity(pedidoService.gerarInfoBoleto(id)).build();
    }
    
    @PATCH
    @Path("/{id}/pagamento-boleto")
    @RolesAllowed("User")
    public Response pagamentoBoleto( @QueryParam("idPedido") Long idPedido, 
    @QueryParam("idBoleto") Long idBoleto) {
       pedidoService.pagamentoBoleto(idPedido, idBoleto);
       return Response.status(Status.CREATED).build();
    }
 
    @PATCH
    @Path("/{id}/status")
    @RolesAllowed({ "Adm" })
    public Response updateStatusPedido(@PathParam("id") Long idPedido, @Valid StatusPedidoDTORequest status) {
        LOG.infof("Atualizando status do pedido com ID: {} para {}", idPedido);
        pedidoService.updateStatusPedido(idPedido, status.idStatus());
        LOG.infof("Status do pedido com ID: {} atualizado com sucesso", idPedido);
        return Response.noContent().build();
    }
}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)