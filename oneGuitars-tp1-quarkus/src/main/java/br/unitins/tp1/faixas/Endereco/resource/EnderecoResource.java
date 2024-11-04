package br.unitins.tp1.faixas.Endereco.resource;

import br.unitins.tp1.faixas.Endereco.dto.EnderecoDTORequest;
import br.unitins.tp1.faixas.Endereco.service.EnderecoService;
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

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource  {
    @Inject
    EnderecoService service;


@GET
@Path("/{id}")
public Response findById(@PathParam("id") Long id){
    return Response.ok(service.findyById(id)).build();
}

@GET
@Path("/search/cep/{cep}")
public Response findByCep(@PathParam("cep") String cep){
    return Response.ok(service.findByCep(cep)).build();
}

@GET
public Response findAll(){
    return Response.ok(service.findAll()).build();
}

@POST
public Response create(@Valid EnderecoDTORequest dto){
    return Response.status(Response.Status.CREATED).entity(dto).build();
}

@PUT
public Response update(@Valid @PathParam("id") Long id, @Valid EnderecoDTORequest dto){
service.update(id, dto);
return Response.noContent().build();
}

@DELETE
@Path("/{id}")
public Response delete(@PathParam("id") Long id){
    service.delete(id);
    return Response.noContent().build();
}
}
