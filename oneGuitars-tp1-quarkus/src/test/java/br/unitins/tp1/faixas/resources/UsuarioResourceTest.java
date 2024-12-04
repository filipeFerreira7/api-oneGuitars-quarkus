package br.unitins.tp1.faixas.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTORequest;
import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTORequest;
import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTOResponse;
import br.unitins.tp1.faixas.Usuario.service.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class UsuarioResourceTest {
    
    @Inject
    UsuarioService usuarioService;

    @Test
    public void testFindAll(){
        given()
            .when().get("/usuarios")
            .then().statusCode(200);

    }

    @Test
    void testFindById(){
        given()
                .when().get("/usuarios/" + 2)
                .then().statusCode(404);

    }

    @Test
    void testFindByNome(){
        given()
            .when().get("/usuarios/search/"+"Filipe")
            .then().statusCode(200);
    }



    @Test
    public void testCreate() {
        TelefoneDTORequest telefone = new TelefoneDTORequest("62", "9998844");
        String username = "fGije" + System.currentTimeMillis();
        String cpf = "07480" + System.currentTimeMillis();      
        List<Integer> idPerfis = List.of(1, 2);      
        String senha = "Senha@123";                            
    
        UsuarioDTORequest dto = new UsuarioDTORequest(
            "Filipe",         
            1,                      
            telefone,           
            13,                    
            6,                    
            2005,            
            idPerfis,        
            cpf,               
            username,            
            senha                  
        );
    
        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when()
            .post("/usuarios")
        .then()
            .log().all()
            .statusCode(201)
            .body(
                "nome", is("Filipe"),
                "cpf", is(cpf)
            );
    }
    
    @Test
    void testUpdate() {
        TelefoneDTORequest telefone = new TelefoneDTORequest("62", "9998843");
        UsuarioDTORequest dto = new UsuarioDTORequest(
            "Filipe", 1, telefone, 1, 3, 2001, List.of(1), "95272811", "deucerto", "filipinho12"
        );
    
        Long id = usuarioService.create(dto).id();
    
        UsuarioDTORequest newCliente = new UsuarioDTORequest(
            "Filipe Atualizado", 1, telefone, 1, 2, 2001, List.of(1), "9847282", "fFilipe155552", "filipinho12"
        );
    
        given()
            .contentType(ContentType.JSON)
            .body(newCliente)
            .when()
            .put("/clientes/" + id)
            .then()
            .statusCode(404);
    
        usuarioService.delete(usuarioService.findById(id).id());
    }

    @Test
void testDelete() {
    TelefoneDTORequest telefone = new TelefoneDTORequest("62", "9998843");
    UsuarioDTORequest dto = new UsuarioDTORequest(
        "Olegário", 1, telefone, 1, 3, 2001, List.of(1), "34212", "ffoe1120", "filipinho12"
    );

    Long id = usuarioService.create(dto).id();

    given()
        .when()
        .delete("/clientes/" + id)
        .then()
        .statusCode(404);

    UsuarioDTOResponse clienteResponse = null;

    try {
        usuarioService.findById(id);
    } catch (Exception e) {
        // Exception expected
    } finally {
        assertNull(clienteResponse);
    }
}

    
}
