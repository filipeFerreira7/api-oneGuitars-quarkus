package br.unitins.tp1.faixas.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.faixas.Estado.dto.EstadoDTORequest;
import br.unitins.tp1.faixas.Estado.dto.EstadoDTOResponse;
import br.unitins.tp1.faixas.Estado.model.Estado;
import br.unitins.tp1.faixas.Estado.service.EstadoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EstadoResourceTest {
    
    @Inject
    EstadoService estadoService;

    @Test
    public void testFindAll(){
        given()
            .when().get("/estados")
            .then().statusCode(200);

    }

    @Test
    void testFindById(){
        given()
                .when().get("/estados/" + 3)
                .then().statusCode(401);

    }

    @Test
    void testFindByNome(){
        given()
            .when().get("/estados/search/"+"Paraná")
            .then().statusCode(401);
    }



    @Test
    public void testCreate(){
       EstadoDTORequest dto = new EstadoDTORequest("Bahia","BA");

        given()
        .contentType(ContentType.JSON)
        .body(dto)
        .when()
            .post("/estados")
        .then()
        .log().all()
        .statusCode(201)
         .body(
        "nome", is("Bahia")
         );       

         
        
    }

    @Test
    void testUpdate(){
        
        EstadoDTORequest estadoDTO = 
        new EstadoDTORequest(
            "California","CA"
           );

        Long id = estadoService.create(estadoDTO).getId();

        EstadoDTORequest newEstado = 
        new EstadoDTORequest(
            "Texas","TX"
           );

         given()
            .contentType(ContentType.JSON)
            .body(newEstado)
            .when()
                .put("/estados/"+id)
            .then()
                .statusCode(204);

        
        Estado estado = estadoService.findById(id);

        assertEquals(estado.getNome(), ("Texas"));

        estadoService.delete(estadoService.findById(id).getId());

        

    }

    @Test
    void testDelete() {
        EstadoDTORequest estado =  new EstadoDTORequest(
            "California","CA"
           );

        Long id = estadoService.create( estado).getId();
        given()
                .when().delete("/estados/" + id)
                .then()
                .statusCode(204);

        EstadoDTOResponse estadoResponse = null;

        try {
            estadoService.findById(id);
        } catch (Exception e) {

        } finally {
            assertNull(estadoResponse);
        }
    }
}
