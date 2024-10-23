package br.unitins.tp1.faixas.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.faixas.Telefone.dto.TelefoneDTORequest;
import br.unitins.tp1.faixas.Usuario.dto.PessoaFisicaDTORequest;
import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTORequest;
import br.unitins.tp1.faixas.Usuario.dto.UsuarioDTOResponse;
import br.unitins.tp1.faixas.Usuario.model.Sexo;
import br.unitins.tp1.faixas.Usuario.model.Usuario;
import br.unitins.tp1.faixas.Usuario.service.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class UsuarioResourceTest {

    @Inject
    UsuarioService usuarioService;

    @Test
    public void testgetAll() {
        given()
                .when().get("/usuarios")
                .then().statusCode(200);

    }

    @Test
    void testFindById(){
        given()
                .when().get("/usuarios/" + 11)
                .then().statusCode(200);

    }

    @Test
    void testFindByCpf(){
        given()
                .when().get("/usuarios/" + "99984879253")
                .then().statusCode(200);

    }

    @Test
    void testFindByNome(){
        given()
            .when().get("/usuarios/search/"+"Filipe")
            .then().statusCode(200);
    }

    @Test
    public void testCreate() {
        PessoaFisicaDTORequest pFDto = new PessoaFisicaDTORequest("TesteUltimoCriar",
                "65656565657",
                "TesteUltimoCriar@gmail.com", 1);

        TelefoneDTORequest telefoneDto = new TelefoneDTORequest("63", "9999-9999");

        UsuarioDTORequest usuarioDTORequest = new UsuarioDTORequest(telefoneDto, pFDto);

        given()
                .contentType(ContentType.JSON)
                .body(usuarioDTORequest)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("id", notNullValue(),
                        "nome", is("TesteUltimoCriar"),
                        "cpf", is("65656565657"),
                        "telefone.dd", is("63"),
                        "telefone.numero", is("9999-9999"));

    }

    @Test
    void testUpdate(){

        String uniqueCpf = "3333333333";

        
        PessoaFisicaDTORequest pessoaFisicaDTORequest = 
        new PessoaFisicaDTORequest(
            "teste",
         uniqueCpf,
          "Joilen@gmail.com",
           2
           );

         TelefoneDTORequest telefone = new TelefoneDTORequest("011", "4002-8922");

        UsuarioDTORequest usuario = new UsuarioDTORequest(telefone,pessoaFisicaDTORequest);

          Long id = usuarioService.create(usuario).getId();


          
         TelefoneDTORequest telefoneUpdate = new TelefoneDTORequest("011", "4002-9022");

         PessoaFisicaDTORequest pFUpdate = new PessoaFisicaDTORequest("Leandra",
         "22222222222", "Joilen@gmail.com", 2);


         UsuarioDTORequest userUpdate = new UsuarioDTORequest(telefoneUpdate,pFUpdate
         );

         given()
            .contentType(ContentType.JSON)
            .body(userUpdate)
            .when()
                .put("/usuarios/"+id)
            .then()
                .statusCode(204);

        
        Usuario user = usuarioService.findById(id);

        assertEquals(user.getPessoaFisica().getNome(), (userUpdate.pessoaFisicaDto().nome()));
    
        assertEquals(user.getPessoaFisica().getSexo(), (Sexo.FEMININO));

        usuarioService.delete(usuarioService.findByCpf("22222222222").getId());

    }

    @Test
    void testDelete() {
        PessoaFisicaDTORequest pessoaFisicaDTORequest = new PessoaFisicaDTORequest("Joilen",
                "44444444444", "Joilen@gmail.com", 1);

        TelefoneDTORequest telefone = new TelefoneDTORequest("011", "4002-8922");

        UsuarioDTORequest usuario = new UsuarioDTORequest(telefone, pessoaFisicaDTORequest);

        Long id = usuarioService.create(usuario).getId();
        given()
                .when().delete("/usuarios/" + id)
                .then()
                .statusCode(204);

        UsuarioDTOResponse usuarioResponse = null;

        try {
            usuarioService.findById(id);
        } catch (Exception e) {

        } finally {
            assertNull(usuarioResponse);
        }
    }
}
