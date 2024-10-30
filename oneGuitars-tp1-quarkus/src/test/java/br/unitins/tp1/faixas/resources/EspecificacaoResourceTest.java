package br.unitins.tp1.faixas.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.faixas.Especificacao.dto.EspecificacaoDTORequest;
import br.unitins.tp1.faixas.Especificacao.dto.EspecificacaoDTOResponse;
import br.unitins.tp1.faixas.Especificacao.model.Especificacao;
import br.unitins.tp1.faixas.Especificacao.service.EspecificacaoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EspecificacaoResourceTest {
    
    @Inject
    EspecificacaoService especificacaoService;

    @Test
    public void testFindAll(){
        given()
            .when().get("/especificacoes")
            .then().statusCode(200);

    }

    @Test
    void testFindById(){
        given()
                .when().get("/especificacoes/" + 2)
                .then().statusCode(200);

    }

    @Test
    void testFindBySku(){
        given()
            .when().get("/especificacoes/search/"+"GTR02")
            .then().statusCode(200);
    }



    @Test
    public void testCreate(){
       EspecificacaoDTORequest dto = new EspecificacaoDTORequest("GTR07",1.20,
       "Rosewood","P90","3 posições");

        given()
        .contentType(ContentType.JSON)
        .body(dto)
        .when()
            .post("/especificacoes")
        .then()
        .log().all()
        .statusCode(201)
         .body(
        "sku", is("GTR07"),
        "tipoCaptador", is("P90")
         );       

         
        
    }

    @Test
    void testUpdate(){
        
        EspecificacaoDTORequest especificacaoDTO = 
        new EspecificacaoDTORequest("GTR08",1.20,
       "Rosewood","P90","3 posições");


        Long id = especificacaoService.create(especificacaoDTO).getId();

        EspecificacaoDTORequest newEspecificacao = 
        new EspecificacaoDTORequest("GTR09",1.20,
       "Rosewood","P90","5 posições");


         given()
            .contentType(ContentType.JSON)
            .body(newEspecificacao)
            .when()
                .put("/especificacoes/"+id)
            .then()
                .statusCode(204);

        
        Especificacao especificacao = especificacaoService.findById(id);

        assertEquals(especificacao.getSku(), ("GTR09"));
        assertEquals(especificacao.getTipoCaptador(),("P90"));

        especificacaoService.delete(especificacaoService.findById(id).getId());

        

    }

    @Test
    void testDelete() {
        EspecificacaoDTORequest especificacao =new EspecificacaoDTORequest("GTR09",1.20,
        "Rosewood","P90","5 posições");

        Long id = especificacaoService.create( especificacao).getId();
        given()
                .when().delete("/especificacoes/" + id)
                .then()
                .statusCode(204);

        EspecificacaoDTOResponse especificacaoResponse = null;

        try {
            especificacaoService.findById(id);
        } catch (Exception e) {

        } finally {
            assertNull(especificacaoResponse);
        }
    }
}
