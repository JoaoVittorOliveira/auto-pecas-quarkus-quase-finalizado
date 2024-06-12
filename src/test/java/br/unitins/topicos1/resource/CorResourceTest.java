package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.CorDTO;
import br.unitins.topicos1.dto.CorResponseDTO;
import br.unitins.topicos1.service.CorService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class CorResourceTest {
    @Inject
    public CorService corService;

    @Test
    public void findAllTest() {
        given()
                .when()
                .get("/cores")
                .then()
                .statusCode(200)
                .body("nome", hasItem(is("Vermelho")));
    }

    @Test
    public void findByIdTest() {
        given()
                .when()
                .get("/cores/1")
                .then()
                .statusCode(200)
                .body("id", is(1));
    }

    @Test
    public void findByNomeTest() {
        given()
                .when()
                .get("/cores/search/nome/Vermelho")
                .then()
                .statusCode(200)
                .body("nome", everyItem(is("Vermelho")))
                .body("nome", hasItem(is("Vermelho")));

    }

    @Test
    public void findByCodigoTest() {
        given()
                .when()
                .get("/cores/search/codigo/A3A3A3")
                .then()
                .statusCode(200)
                .body("codigo", is("A3A3A3"));

    }

    @Test
    public void createTest() {
        CorDTO dto = new CorDTO("Teste", "010101");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .when()
                .post("/cores")
                .then()
                .statusCode(201)
                .body("nome", is("Teste"))
                .body("codigo", is("010101"));

    }

    @Test
    public void updateTest() {
        CorDTO dto = new CorDTO("Teste PUT", "AAAAAA");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .when()
                .pathParam("id", 2)
                .put("/cores/{id}")
                .then()
                .statusCode(204);

    }

    @Test
    public void deleteTest() {
        CorDTO dto = new CorDTO("Teste Delete", "000000");
        CorResponseDTO response =  corService.create(dto);
        Long id = response.id();

        given()
                .when()
                .pathParam("id", id)
                .delete("/cores/{id}")
                .then()
                .statusCode(204);


//        corService.delete(id);
//        assertNull(corService.getById(response.id()));
    }
}
