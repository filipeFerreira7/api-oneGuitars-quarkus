package br.unitins.tp1.faixas.resources;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.faixas.Endereco.service.EnderecoService;
import br.unitins.tp1.faixas.Pedido.service.PedidoService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;

@QuarkusTest
public class PedidoResourceTest {

    @Inject
    PedidoService pedidoService;
    @Inject
    EnderecoService enderecoService;
    

    @Test
    @TestSecurity(user = "test", roles ={"Adm","User"})
    void testFindById() {
        given()
                .when().get("/pedidos/" + 1)
                .then().statusCode(404);

    }

    @Test
    @TestSecurity(user = "test", roles ={"Adm","User"})
    void testFindByUsername() {
        given()
                .when().get("/marcas/search/" + "carlinhos")
                .then().statusCode(404);
    }

}
