package br.unitins.tp1.faixas.Usuario.resource;

import java.util.List;

import br.unitins.tp1.faixas.Usuario.dto.PessoaFisicaDTORequest;
import br.unitins.tp1.faixas.Usuario.dto.PessoaFisicaDTOResponse;
import br.unitins.tp1.faixas.Usuario.service.PessoaFisicaService;
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


@Path("/pessoasfisicas")
public class PessoaFisicaResource {
    
    @Inject
    public PessoaFisicaService pessoaFisicaService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        
        return Response.ok(PessoaFisicaDTOResponse.valueOf(pessoaFisicaService.findById(id))).build();
    }

    @GET
    @Path("/search/{nome}")
    public List<PessoaFisicaDTOResponse> findByNome(@PathParam("nome")String nome){
        return pessoaFisicaService.findByNome(nome).
                     stream().
                     map(o -> PessoaFisicaDTOResponse.valueOf(o))
                    .toList();
    }

    @GET
    public Response findAll(){
        return Response.ok(pessoaFisicaService.
                     findAll().
                     stream().
                     map(o -> PessoaFisicaDTOResponse.valueOf(o))
                    .toList()).build();
        
    }

    @POST
    public Response create(@Valid PessoaFisicaDTORequest dto){
        return  Response.status(Status.CREATED).entity(
            PessoaFisicaDTOResponse.valueOf(pessoaFisicaService.create(dto))
            ).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@Valid @PathParam("id") Long id, @Valid PessoaFisicaDTORequest dto){
        pessoaFisicaService.update(id, dto);
       return Response.noContent().build();
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        pessoaFisicaService.delete(id);
        return Response.noContent().build();
    }
}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)