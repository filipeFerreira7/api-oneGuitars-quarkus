package br.unitins.tp1.faixas.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.faixas.Marca.dto.MarcaDTORequest;
import br.unitins.tp1.faixas.Marca.dto.MarcaDTOResponse;
import br.unitins.tp1.faixas.Marca.model.Marca;
import br.unitins.tp1.faixas.Marca.service.MarcaService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class MarcaResourceTest {
    
    @Inject
    MarcaService marcaService;

    @Test
    public void testFindAll(){
        given()
            .when().get("/marcas")
            .then().statusCode(200);

    }

    @Test
    void testFindById(){
        given()
                .when().get("/marcas/" + 2)
                .then().statusCode(200);

    }

    @Test
    void testFindByNome(){
        given()
            .when().get("/marcas/search/"+"Fender")
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
    void testUpdate(){
        
        MarcaDTORequest marcaDTO = 
        new MarcaDTORequest(
            "teste"
           );

        Long id = marcaService.create(marcaDTO).getId();

        MarcaDTORequest newMarca = 
        new MarcaDTORequest(
            "Fender"
           );

         given()
            .contentType(ContentType.JSON)
            .body(newMarca)
            .when()
                .put("/marcas/"+id)
            .then()
                .statusCode(204);

        
        Marca marca = marcaService.findById(id);

        assertEquals(marca.getNome(), ("Fender"));

        marcaService.delete(marcaService.findById(id).getId());

        

    }

    @Test
    void testDelete() {
        MarcaDTORequest marca = new MarcaDTORequest("Fender");

        Long id = marcaService.create( marca).getId();
        given()
                .when().delete("/marcas/" + id)
                .then()
                .statusCode(204);

        MarcaDTOResponse marcaResponse = null;

        try {
            marcaService.findById(id);
        } catch (Exception e) {

        } finally {
            assertNull(marcaResponse);
        }
    }
}
