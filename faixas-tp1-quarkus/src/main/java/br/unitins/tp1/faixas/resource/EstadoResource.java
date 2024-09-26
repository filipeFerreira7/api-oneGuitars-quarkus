package br.unitins.tp1.faixas.resource;

import java.util.List;

import br.unitins.tp1.faixas.DTO.EstadoDTO;
import br.unitins.tp1.faixas.model.Estado;
import br.unitins.tp1.faixas.service.EstadoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;


@Path("/estados")
public class EstadoResource {
    
    @Inject
    public EstadoService estadoService;

    @GET
    @Path("/{id}")
    public Estado findById(Long id){
        return estadoService.findById(id);
    }

    @GET
    @Path("/search/{nome}")
    public List<Estado> findByNome(String nome){
        return estadoService.findByNome(nome);
    }

    @GET
    public List<Estado> findAll(){
        return estadoService.findAll();
    }

    @POST
    public Estado create(EstadoDTO estado){

        return estadoService.create(estado);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id,EstadoDTO dto){
        estadoService.update(id,dto);
    }
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id){
        estadoService.delete(id);
    }
}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)