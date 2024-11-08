package br.unitins.tp1.faixas.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.faixas.Cidade.dto.CidadeDTOResponse;
import br.unitins.tp1.faixas.Endereco.dto.EnderecoDTORequest;
import br.unitins.tp1.faixas.Endereco.model.Endereco;
import br.unitins.tp1.faixas.Endereco.service.EnderecoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;


@QuarkusTest
public class EnderecoResourceTest {
    
    @Inject
    EnderecoService service;

    @Test
  
    public void testFindAll(){
        given()
            .when().get("/enderecos")
            .then().statusCode(200);

    }

    @Test

    void testFindById(){
        given()
                .when().get("/enderecos/" + 1)
                .then().statusCode(200);

    }

    @Test

    void testFindByCep(){
        given()
            .when().get("/enderecos/search/"+"77006")
            .then().statusCode(200);
    }



    @Test

    public void testCreate(){
       EnderecoDTORequest dto = new EnderecoDTORequest("404 N al lt 07","plano norte","77006452",1L);
        given()
        .contentType(ContentType.JSON)
        .body(dto)
        .when()
            .post("/enderecos")
        .then()
        .log().all()
        .statusCode(201)
         .body(
        "cep", is("77006452")
         );       
         
        
    }

    @Test
    void testUpdate(){
        
        EnderecoDTORequest enderecoDTO = 
        new EnderecoDTORequest(
            "404 N al 28 lt 07","plano Diretor norte","6626041",1L);

        Long id = service.create(enderecoDTO).id();


        EnderecoDTORequest newEndereco = 
        new EnderecoDTORequest(
            "404 N Atualizado","plano Diretor norte novo","6626031",1L);

         given()
            .contentType(ContentType.JSON)
            .body(newEndereco)
            .when()
                .put("/cidades/"+id)
            .then()
                .statusCode(204);

        
        Endereco endereco = service.findyById(id);

        assertEquals(endereco.getCep(), ("770001"));

        service.delete(service.findyById(id).getId());
        

    }

    @Test
    void testDelete() {
         EnderecoDTORequest endereco = 
        new EnderecoDTORequest(
            "106 N al 12 lt 07","plano Diretor norte novo","7702039",1L);

        Long id = service.create(endereco).id();
        given()
                .when().delete("/cidades/" + id)
                .then()
                .statusCode(204);

        CidadeDTOResponse cidadeResponse = null;

        try {
            service.findyById(id);
        } catch (Exception e) {

        } finally {
            assertNull(cidadeResponse);
        }
    }
}
