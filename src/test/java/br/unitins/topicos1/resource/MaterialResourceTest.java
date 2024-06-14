package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.MaterialDTO;
import br.unitins.topicos1.dto.MaterialResponseDTO;
import br.unitins.topicos1.service.MaterialService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class MaterialResourceTest {
    @Inject
    public MaterialService materialService;

    @Test
    public void findAllTest() {
        given()
                .when()
                .get("/materiais")
                .then()
                .statusCode(200);
    }

    @Test
    public void findByIdTest() {
        given()
                .when()
                .get("/materiais/1")
                .then()
                .statusCode(200)
                .body("id", is(1));
    }

    @Test
    public void findByNomeTest() {
        given()
                .when()
                .get("/materiais/search/nome/Fibra")
                .then()
                .statusCode(200)
                .body("nome", hasItem(is("Fibra de Carbono")));
    }

    @Test
    public void createTest() {
        MaterialDTO dto = new MaterialDTO("Teste");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .when()
                .post("/materiais")
                .then()
                .statusCode(201)
                .body("nome", is("Teste"));

    }

    @Test
    public void updateTest() {
        MaterialDTO dto = new MaterialDTO("Teste PUT");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .when()
                .pathParam("id", 2)
                .put("/materiais/{id}")
                .then()
                .statusCode(204);

    }

    @Test
    public void deleteTest() {
        MaterialDTO dto = new MaterialDTO("Teste Delete");
        MaterialResponseDTO response = materialService.create(dto);

        given()
                .when()
                .pathParam("id", response.id())
                .delete("/materiais/{id}")
                .then()
                .statusCode(204);


//        materialService.delete(id);
//        assertNull(materialService.getById(response.id()));
    }
}
