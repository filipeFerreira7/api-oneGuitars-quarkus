package br.unitins.tp1.faixas.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.faixas.Marca.dto.MarcaDTORequest;
import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTORequest;
import br.unitins.tp1.faixas.Usuario.dto.PessoaFisicaDTORequest;
import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTORequest;
import br.unitins.tp1.faixas.Usuario.model.Sexo;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class UsuarioResourceTest {
    
    @Test
    public void testgetAll(){
        given()
            .when().get("/usuarios")
            .then().statusCode(200);

    }


    @Test
    public void testCreate(){
       PessoaFisicaDTORequest pFDto = new PessoaFisicaDTORequest("Joilen",
        "99988877753",
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
              "nome", is("Filipe"),

              "cpf", is("4444"),
              "telefone.dd", is("63"),
              "telefone.numero", is("9999-9999")
         );       

         
        
    }

    @Test
    public void tetstDelete(){
        long id = 3;
    }
}
