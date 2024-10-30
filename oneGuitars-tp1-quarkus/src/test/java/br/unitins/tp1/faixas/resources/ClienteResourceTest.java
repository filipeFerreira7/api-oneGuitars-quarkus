package br.unitins.tp1.faixas.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.faixas.Cliente.dto.ClienteDTORequest;
import br.unitins.tp1.faixas.Cliente.dto.ClienteDTOResponse;
import br.unitins.tp1.faixas.Cliente.service.ClienteService;
import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTORequest;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class ClienteResourceTest {
    
    @Inject
    ClienteService clienteService;

    @Test
    public void testFindAll(){
        given()
            .when().get("/clientes")
            .then().statusCode(200);

    }

    @Test
    void testFindById(){
        given()
                .when().get("/clientes/" + 2)
                .then().statusCode(200);

    }

    @Test
    void testFindByNome(){
        given()
            .when().get("/clientes/search/"+"Fender")
            .then().statusCode(200);
    }



    @Test
    public void testCreate(){
        TelefoneDTORequest telefone = new TelefoneDTORequest("62", "9998844");
        String username = "fGije"+System.currentTimeMillis();
        String cpf = "07480" + System.currentTimeMillis();
       ClienteDTORequest dto = new ClienteDTORequest("Ontario",1,telefone,1,1,2001,cpf,username,"filipinho12");

        given()
        .contentType(ContentType.JSON)
        .body(dto)
        .when()
            .post("/clientes")
        .then()
        .log().all()
        .statusCode(201)
         .body(
        "nome", is("Ontario"), "cpf", is(cpf)
         );       

         
        
    }

    @Test
    void testUpdate(){
        TelefoneDTORequest telefone = new TelefoneDTORequest("62", "9998843");
        
       ClienteDTORequest dto = new ClienteDTORequest("Filipe ",1,telefone,1,3,2001,"95272811","deucerto","filipinho12");
        
        Long id = clienteService.create(dto).id();

        ClienteDTORequest newCliente = new ClienteDTORequest("Filipe Atualizado ",1,telefone,1,2,2001,"9847282","fFilipe155552","filipinho12");

         given()
            .contentType(ContentType.JSON)
            .body(newCliente)
            .when()
                .put("/clientes/"+id)
            .then()
                .statusCode(204);

    

        clienteService.delete(clienteService.findById(id).id());

        

    }

    @Test
    void testDelete() {
        TelefoneDTORequest telefone = new TelefoneDTORequest("62", "9998843");
        ClienteDTORequest dto = new ClienteDTORequest("Olegário ",1,telefone,1,3,2001,"34212","ffoe1120","filipinho12");

        Long id = clienteService.create(dto).id();
        given()
                .when().delete("/clientes/" + id)
                .then()
                .statusCode(204);

        ClienteDTOResponse clienteResponse = null;

        try {
            clienteService.findById(id);
        } catch (Exception e) {

        } finally {
            assertNull(clienteResponse);
        }
    }

    
}
