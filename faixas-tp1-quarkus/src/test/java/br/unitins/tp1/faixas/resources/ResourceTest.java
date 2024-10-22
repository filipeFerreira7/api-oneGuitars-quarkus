package br.unitins.tp1.faixas.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.faixas.Marca.dto.MarcaDTORequest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class ResourceTest {
    
    @Test
    public void testFindAll(){
        given()
            .when().get("/clientes")
            .then().statusCode(200);

    }


    @Test
    public void testCreate(){
       MarcaDTORequest dto = new MarcaDTORequest("Tagima");

        given()
        .contentType(ContentType.JSON)
        .body(dto)
        .when()
            .post("/marcas")
        .then()
        .log().all()
        .statusCode(201)
         .body(
        "nome", is("Tagima")
         );       

         
        
    }

    @Test
    public void tetstDelete(){
        long id = 3;
    }
}
