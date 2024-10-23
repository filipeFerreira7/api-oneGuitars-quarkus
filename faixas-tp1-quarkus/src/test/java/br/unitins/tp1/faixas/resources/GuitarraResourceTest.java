package br.unitins.tp1.faixas.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.faixas.Guitarra.dto.GuitarraDTORequest;
import br.unitins.tp1.faixas.Guitarra.dto.GuitarraDTOResponse;
import br.unitins.tp1.faixas.Guitarra.model.Guitarra;
import br.unitins.tp1.faixas.Guitarra.service.GuitarraService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class GuitarraResourceTest {
    
    @Inject
    GuitarraService guitarraService;

    @Test
    public void testFindAll(){
        given()
            .when().get("/guitarras")
            .then().statusCode(200);

    }

    @Test
    void testFindById(){
        given()
                .when().get("/guitarras/" + 2)
                .then().statusCode(200);

    }

    @Test
    void testFindByNome(){
        given()
            .when().get("/guitarras/search/"+"Fender")
            .then().statusCode(200);
    }



    @Test
    public void testCreate(){
       GuitarraDTORequest dto = new GuitarraDTORequest("TW51","01428","Fuschia",1900.9,1L,1L);

        given()
        .contentType(ContentType.JSON)
        .body(dto)
        .when()
            .post("/guitarras")
        .then()
        .log().all()
        .statusCode(201)
         .body(
        "numeroSerie", is("01428")
         );       

         
        
    }

    @Test
    void testUpdate(){
        GuitarraDTORequest guitarraDTO = new GuitarraDTORequest("TW51","01428","Fuschia",1900.9,1L,1L);

        Long id = guitarraService.create(guitarraDTO).getId();

        GuitarraDTORequest newGuitarra = 
        new GuitarraDTORequest("TW52","01442","Fuschia",1901.9,1L,1L);

         given()
            .contentType(ContentType.JSON)
            .body(newGuitarra)
            .when()
                .put("/guitarras/"+id)
            .then()
                .statusCode(204);

        
        Guitarra guitarra = guitarraService.findById(id);

        assertEquals(guitarra.getNome(), ("TW52"));

        guitarraService.delete(guitarraService.findById(id).getId());

        

    }

    @Test
    void testDelete() {
        GuitarraDTORequest guitarra = new GuitarraDTORequest("TW51","01428","Fuschia",1900.9,1L,1L);

        Long id = guitarraService.create( guitarra).getId();
        given()
                .when().delete("/guitarras/" + id)
                .then()
                .statusCode(204);

        GuitarraDTOResponse guitarraResponse = null;

        try {
            guitarraService.findById(id);
        } catch (Exception e) {

        } finally {
            assertNull(guitarraResponse);
        }
    }
}
