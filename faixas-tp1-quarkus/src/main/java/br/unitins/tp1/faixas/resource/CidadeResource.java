package br.unitins.tp1.faixas.resource;

import br.unitins.tp1.faixas.model.Cidade;
import br.unitins.tp1.faixas.service.CidadeService;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;


@Path("/cidades")
public class CidadeResource {
    
    @Inject
    public CidadeService cidadeService;

    @GET
    @Path("/{id}")
    public Cidade findById(Long id){
        return cidadeService.findById(id);
    }

    @GET
    @Path("/search/{nome}")
    public List<Cidade> findByNome(String nome){
        return cidadeService.findByNome(nome);
    }

    @GET
    public List<Cidade> findAll(){
        return cidadeService.findAll();
    }

    @POST
    public Cidade create(Cidade cidade){
        return cidadeService.create(cidade);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") Long id,Cidade cidade){
        cidadeService.update(cidade);
    }
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id){
        cidadeService.delete(id);
    }
}

// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)