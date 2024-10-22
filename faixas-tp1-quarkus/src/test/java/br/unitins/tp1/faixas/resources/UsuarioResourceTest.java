package br.unitins.tp1.faixas.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTORequest;
import br.unitins.tp1.faixas.Usuario.dto.PessoaFisicaDTORequest;
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
    public void testgetAll(){
        given()
            .when().get("/usuarios")
            .then().statusCode(200);

    }


    @Test
    public void testCreate(){
       PessoaFisicaDTORequest pFDto = new PessoaFisicaDTORequest("Joilen",
        "99984879253",
         "Joilen@gmail.com", 1);

         TelefoneDTORequest telefoneDto = new TelefoneDTORequest("63","9999-9999");

         UsuarioDTORequest usuarioDTORequest = new UsuarioDTORequest(telefoneDto, pFDto);

        given()
            .contentType(ContentType.JSON)
            .body(usuarioDTORequest)
            .when()
                .post("/usuarios")
            .then()
            .statusCode(201)
                .body("id", notNullValue(),
              "nome", is("Joilen"),
              "cpf", is("99984879253"),
              "telefone.dd", is("63"),
              "telefone.numero", is("9999-9999")
         );       

         
        
    }

    @Test
    void testDelete(){
        PessoaFisicaDTORequest pessoaFisicaDTORequest = new PessoaFisicaDTORequest("Joilen",
         "99984879253", "Joilen@gmail.com", 1);

         TelefoneDTORequest telefone = new TelefoneDTORequest("011", "4002-8922");

         UsuarioDTORequest usuario = new UsuarioDTORequest(telefone, pessoaFisicaDTORequest);

         Long id = usuarioService.create(usuario).getId();
          given()
         .when().delete("/usuarios/" + id)
         .then()
         .statusCode(204);
         
        UsuarioDTOResponse usuarioResponse = null;
        

         try{
            usuarioService.findById(id);
         }catch(Exception e){

         }
         finally{
            assertNull(usuarioResponse);
         }
    }
}
