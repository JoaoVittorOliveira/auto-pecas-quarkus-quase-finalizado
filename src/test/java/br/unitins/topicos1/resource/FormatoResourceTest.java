package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.FormatoDTO;
import br.unitins.topicos1.dto.FormatoResponseDTO;
import br.unitins.topicos1.service.FormatoService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class FormatoResourceTest {
    @Inject
    public FormatoService formatoService;

    @Test
    public void findAllTest() {
        given()
                .when()
                .get("/formatos")
                .then()
                .statusCode(200);
    }

    @Test
    public void findByIdTest() {
        given()
                .when()
                .get("/formatos/1")
                .then()
                .statusCode(200)
                .body("id", is(1));
    }

    @Test
    public void findByDescricaoFormatoTest() {
        given()
                .when()
                .get("/formatos/search/Formato")
                .then()
                .statusCode(200);
//                .body("descricao", everyItem(is("Formato")));
    }

    @Test
    public void createTest() {
        FormatoDTO dto = new FormatoDTO("Teste");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .when()
                .post("/formatos")
                .then()
                .statusCode(201)
                .body("descricaoFormato", is("Teste"));

    }

    @Test
    public void updateTest() {
        FormatoDTO dto = new FormatoDTO("Teste PUT");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .when()
                .pathParam("id", 2)
                .put("/formatos/{id}")
                .then()
                .statusCode(204);

    }

    @Test
    public void deleteTest() {
        FormatoDTO dto = new FormatoDTO("Teste Delete");
        FormatoResponseDTO response = formatoService.create(dto);

        given()
                .when()
                .pathParam("id", response.id())
                .delete("/formatos/{id}")
                .then()
                .statusCode(204);


//        formatoService.delete(id);
//        assertNull(formatoService.getById(response.id()));
    }
}
