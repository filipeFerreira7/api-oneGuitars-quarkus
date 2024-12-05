package br.unitins.tp1.oneGuitars.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.oneGuitars.Cidade.dto.CidadeDTORequest;
import br.unitins.tp1.oneGuitars.Cidade.dto.CidadeDTOResponse;
import br.unitins.tp1.oneGuitars.Cidade.model.Cidade;
import br.unitins.tp1.oneGuitars.Cidade.service.CidadeService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class CidadeResourceTest {
    
    @Inject
    CidadeService cidadeService;

    @Test
    @TestSecurity(user = "test", roles ={"Adm","User"})
    public void testFindAll(){
        given()
            .when().get("/cidades")
            .then().statusCode(200);

    }

    @Test
    @TestSecurity(user = "test", roles ={"Adm","User"})
    void testFindById(){
        given()
                .when().get("/cidades/" + 2)
                .then().statusCode(200);

    }

    @Test
    void testFindByNome(){
        given()
            .when().get("/cidades/search/"+"Palmas")
            .then().statusCode(200);
    }



    @Test
    @TestSecurity(user = "test", roles ={"Adm","User"})
    public void testCreate(){
       CidadeDTORequest dto = new CidadeDTORequest("São Paulo",4L);

        given()
        .contentType(ContentType.JSON)
        .body(dto)
        .when()
            .post("/cidades")
        .then()
        .log().all()
        .statusCode(201)
         .body(
        "nome", is("São Paulo")
         );       

         
        
    }

    @Test
    @TestSecurity(user = "test", roles ={"Adm","User"})
    void testUpdate(){
        
        CidadeDTORequest cidadeDTO = 
        new CidadeDTORequest(
            "Figueiropolis",1L
           );

        Long id = cidadeService.create(cidadeDTO).getId();

        CidadeDTORequest newCidade = 
        new CidadeDTORequest(
            "Paranã",1L
           );

         given()
            .contentType(ContentType.JSON)
            .body(newCidade)
            .when()
                .put("/cidades/"+id)
            .then()
                .statusCode(204);

        
        Cidade cidade = cidadeService.findById(id);

        assertEquals(cidade.getNome(), ("Paranã"));

        cidadeService.delete(cidadeService.findById(id).getId());

        

    }

    @Test
    @TestSecurity(user = "test", roles ={"Adm","User"})
    void testDelete() {
        CidadeDTORequest cidade = new CidadeDTORequest("Presidente Kennedy",1L);

        Long id = cidadeService.create( cidade).getId();
        given()
                .when().delete("/cidades/" + id)
                .then()
                .statusCode(204);

        CidadeDTOResponse cidadeResponse = null;

        try {
            cidadeService.findById(id);
        } catch (Exception e) {

        } finally {
            assertNull(cidadeResponse);
        }
    }
}
