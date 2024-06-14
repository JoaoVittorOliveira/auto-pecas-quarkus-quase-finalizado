package br.unitins.topicos1.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.ItemPedidoDTO;
import br.unitins.topicos1.dto.PedidoDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.service.PedidoService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class PedidoResourceTest {
    @Inject
    public PedidoService service;

    @Test
    public void findAllTest() {
        given()
                .when()
                .get("/pedidos")
                .then()
                .statusCode(200);
    }

    @Test
    public void findByIdTest() {
        given()
                .when()
                .get("/pedidos/search/id/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void findByClienteTest(){
        given()
                .when()
                .get("/pedidos/search/cliente/id/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void createTest() {

        List<ItemPedidoDTO> itens = new ArrayList<ItemPedidoDTO>();
        ItemPedidoDTO item = new ItemPedidoDTO(0d, 2, 1l);
        itens.add(item);

        PedidoDTO dto = new PedidoDTO(
                                1l,
                                itens
                             );

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .when()
                .post("/pedidos")
                .then()
                .statusCode(200)
                .body("StatusPagamentoPedido", is("Pedido Não Pago"));

    }

}
