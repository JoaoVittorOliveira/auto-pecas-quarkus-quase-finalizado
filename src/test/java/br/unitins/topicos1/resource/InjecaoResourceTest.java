package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.InjecaoDTO;
import br.unitins.topicos1.dto.InjecaoResponseDTO;
import br.unitins.topicos1.service.InjecaoService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import java.time.LocalDateTime;
import java.util.List;

@QuarkusTest
public class InjecaoResourceTest {
    @Inject
    public InjecaoService injecaoService;

    @Test
    public void findAllTest() {
        given()
                .when()
                .get("/injecoes")
                .then()
                .statusCode(200);
    }

    @Test
    public void findByIdTest() {
        given()
                .when()
                .get("/injecoes/1")
                .then()
                .statusCode(200)
                .body("id", is(1));
    }

    @Test
    public void findByNomeTest() {
        List<String> names = given()
                                .when()
                                .get("/injecoes/search/nome/injecao 1")
                                .jsonPath()
                                .getList("nome");

        assertThat(names, hasItem(equalToIgnoringCase("injecao 1")));    
    }

    @Test
    public void findByCodigoTest() {
        given()
                .when()
                .get("/injecoes/search/codigo/111")
                .then()
                .statusCode(200)
                .body("codigo", hasItem(is("111")));
    }

    @Test
    public void createTest() {

        String nome = LocalDateTime.now() + "_INJECAO_TESTEUNIT";

        InjecaoDTO dto = new InjecaoDTO(nome, "123123", 50, 200.0, "Gasolina");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .when()
                .post("/injecoes")
                .then()
                .statusCode(201)
                .body("nome", is(nome))
                .body("codigo", is("123123"))
                .body("tipoCombustivel", is("Gasolina"));

    }

    @Test
    public void updateTest() {
        InjecaoDTO dto = new InjecaoDTO("Teste PUT", "01010202", 50, 200d, "Etanol");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .when()
                .pathParam("id", 2)
                .put("/injecoes/{id}")
                .then()
                .statusCode(204);

    }

    @Test
    public void deleteTest() {
        InjecaoDTO dto = new InjecaoDTO("Teste Delete", "03030202", 50, 200d, "Etanol");
        InjecaoResponseDTO response = injecaoService.create(dto);

        given()
                .when()
                .pathParam("id", response.id())
                .delete("/injecoes/{id}")
                .then()
                .statusCode(204);
    }
}
