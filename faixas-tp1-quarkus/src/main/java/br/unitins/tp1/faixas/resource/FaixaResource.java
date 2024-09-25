package br.unitins.tp1.faixas.resource;

import br.unitins.tp1.faixas.model.Faixa;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/faixas")
public class FaixaResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON) // produzir(exportar)
    @Consumes // consumir
    public List<Faixa> findAll(){

        return Faixa.listAll();
    }
}
// Views = Swagger and Browser
// Model = Business Rules;(model: Faixa- Entity Class)
// Control = Resource(FaixaResource)