package br.unitins.tp1.oneGuitars.Cancelados.resource;

import java.util.List;

import br.unitins.tp1.oneGuitars.Cancelados.dto.PedidoCanceladoDTOResponse;
import br.unitins.tp1.oneGuitars.Cancelados.model.PedidoCancelado;
import br.unitins.tp1.oneGuitars.Cancelados.service.PedidoCanceladoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/pedidos-cancelados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoCanceladoResource {

    @Inject
    PedidoCanceladoService pedidoCanceladoService;

    @GET
    @Path("/{id}")
    public PedidoCanceladoDTOResponse findById(@PathParam("id") Long id) {
        PedidoCancelado pedidoCancelado = pedidoCanceladoService.findById(id);

        return PedidoCanceladoDTOResponse.valueOf(pedidoCancelado);
    }

    @GET
    @Path("/usuario/{idUsuario}")
    public List<PedidoCanceladoDTOResponse> findByIdUsuario(@PathParam("idUsuario") Long idUsuario) {
        
            List<PedidoCancelado> pedidosCancelados = pedidoCanceladoService.findByIdUsuario(idUsuario);
            return pedidosCancelados.stream()
                    .map(PedidoCanceladoDTOResponse::valueOf)
                    .toList();
        }

    @GET
    @RolesAllowed("Adm")
    public List<PedidoCanceladoDTOResponse> findAll() {
        List<PedidoCancelado> pedidosCancelados = pedidoCanceladoService.findAll();
        return pedidosCancelados.stream()
                .map(PedidoCanceladoDTOResponse::valueOf)
                .toList();
    }
}
