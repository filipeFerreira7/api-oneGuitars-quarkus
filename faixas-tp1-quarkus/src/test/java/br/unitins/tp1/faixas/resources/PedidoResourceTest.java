package br.unitins.tp1.faixas.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.faixas.Pedido.dto.PedidoDTORequest;
import br.unitins.tp1.faixas.Pedido.dto.PedidoDTOResponse;
import br.unitins.tp1.faixas.Pedido.model.Pedido;
import br.unitins.tp1.faixas.Pedido.service.PedidoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class PedidoResourceTest {
    
    @Inject
    PedidoService pedidoService;

    @Test
    public void testFindAll(){
        given()
            .when().get("/pedidos")
            .then().statusCode(200);

    }

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



    @Test
    public void testCreate(){
       PedidoDTORequest dto = new PedidoDTORequest(LocalDateTime.now(),17000.90);

        given()
        .contentType(ContentType.JSON)
        .body(dto)
        .when()
            .post("/pedidos")
        .then()
        .log().all()
        .statusCode(201)
         .body(
            "valorTotal" , is(17000.90F)
         );       

         
        
    }

    @Test
    void testUpdate(){
        
        PedidoDTORequest pedidoDTO = 
        new PedidoDTORequest(
           LocalDateTime.now(),17000.90
           );

        Long id = pedidoService.create(pedidoDTO).getId();

        PedidoDTORequest newPedido = 
        new PedidoDTORequest(
            LocalDateTime.now().minusDays(7),16800.90
           );

         given()
            .contentType(ContentType.JSON)
            .body(newPedido)
            .when()
                .put("/pedidos/"+id)
            .then()
                .statusCode(204);

        
        Pedido pedido = pedidoService.findById(id);

        assertEquals(pedido.getValorTotal(), (16800.90));

        pedidoService.delete(pedidoService.findById(id).getId());

        

    }

    @Test
    void testDelete() {
        PedidoDTORequest pedido = new PedidoDTORequest(LocalDateTime.now().minusDays(7),17000.80);

        Long id = pedidoService.create( pedido).getId();
        given()
                .when().delete("/pedidos/" + id)
                .then()
                .statusCode(204);

        PedidoDTOResponse pedidoResponse = null;

        try {
            pedidoService.findById(id);
        } catch (Exception e) {

        } finally {
            assertNull(pedidoResponse);
        }
    }
}
