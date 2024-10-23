package br.unitins.tp1.faixas.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTORequest;
import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTOResponse;
import br.unitins.tp1.faixas.Telefone.model.Telefone;
import br.unitins.tp1.faixas.Telefone.service.TelefoneService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class TelefoneResourceTest {
    
    @Inject
    TelefoneService telefoneService;

    @Test
    public void testFindAll(){
        given()
            .when().get("/telefones")
            .then().statusCode(200);

    }

    @Test
    void testFindById(){
        given()
                .when().get("/telefones/" + 1)
                .then().statusCode(200);

    }

    @Test
    void testFindByNumero(){
        given()
            .when().get("/telefones/search/"+"40028922")
            .then().statusCode(200);
    }



    @Test
    public void testCreate(){
       TelefoneDTORequest dto = new TelefoneDTORequest("067","40028922");
        given()
        .contentType(ContentType.JSON)
        .body(dto)
        .when()
            .post("/telefones")
        .then()
        .log().all()
        .statusCode(201)
         .body(
        "numero", is("40028922")
         );       

         
        
    }

    @Test
    void testUpdate(){
        
        TelefoneDTORequest telefoneDTO = 
        new TelefoneDTORequest("067","40028922");

        Long id = telefoneService.create(telefoneDTO).getId();

        TelefoneDTORequest newTelefone = 
        new TelefoneDTORequest("067","40028923");

         given()
            .contentType(ContentType.JSON)
            .body(newTelefone)
            .when()
                .put("/telefones/"+id)
            .then()
                .statusCode(204);

        
        Telefone telefone = telefoneService.findById(id);

        assertEquals(telefone.getNumero(),("40028923"));

        telefoneService.delete(telefoneService.findById(id).getId());

        

    }

    @Test
    void testDelete() {
        TelefoneDTORequest telefone = new TelefoneDTORequest("067","40028922");

        Long id = telefoneService.create( telefone).getId();
        given()
                .when().delete("/telefones/" + id)
                .then()
                .statusCode(204);

        TelefoneDTOResponse telefoneResponse = null;

        try {
            telefoneService.findById(id);
        } catch (Exception e) {

        } finally {
            assertNull(telefoneResponse);
        }
    }
}
