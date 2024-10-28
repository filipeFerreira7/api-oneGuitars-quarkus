package br.unitins.tp1.faixas.resources;

import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.faixas.Pedido.service.PedidoService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class PedidoResourceTest {
    
    @Inject
    PedidoService pedidoService;

    @Test
    void testFindById(){
        given()
                .when().get("/pedidos/" + 1)
                .then().statusCode(200);

    }

    @Test
    void testFindByDataCompra(){
        LocalDateTime dateTime = LocalDateTime.of(2024, 10, 16, 14, 48, 46);
        String formattedDateTime = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        given()
            .when().get("/pedidos/search/"+ formattedDateTime)
            .then().statusCode(200);
    }



}