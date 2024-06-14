package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.VolanteDTO;
import br.unitins.topicos1.dto.VolanteResponseDTO;
import br.unitins.topicos1.service.VolanteService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;

import java.time.LocalDateTime;
import java.util.UUID;

@QuarkusTest
public class VolanteResourceTest {
    @Inject
    public VolanteService volanteService;

    @Test
    public void findAllTest() {
        given()
                .when()
                .get("/volantes")
                .then()
                .statusCode(200);
    }

    @Test
    public void findByIdTest() {
        given()
                .when()
                .get("/volantes/1")
                .then()
                .statusCode(400);
    }

    @Test
    public void findByNomeTest() {
        given()
                .when()
                .get("/volantes/search/nome/Volante que nao existe")
                .then()
                .statusCode(200)
                .body("nome", not("Volante que existe"));
    }

    @Test
    public void findByCodigoTest() {
        given()
                .when()
                .get("/volantes/search/codigo/333")
                .then()
                .statusCode(200)
                .body("codigo", is("333"));
    }

    @Test
    public void createTest() {

        String nome = LocalDateTime.now() + "_TESTEUNIT";
        
        VolanteDTO dto = new VolanteDTO(
                                nome,
                                UUID.randomUUID().toString()+"999", 
                                50, 200.0, 
                                "30",  
                                1L,
                                1L,
                                1L
                             );

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .when()
                .post("/volantes")
                .then()
                .statusCode(201)
                .body("nome", is(nome));

    }

    @Test
    public void updateTest() {
        VolanteDTO dto = new VolanteDTO("Teste PUT","333", 50, 200.0, "30",  1L,1L,1L);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .when()
                .pathParam("id", 3)
                .put("/volantes/{id}")
                .then()
                .statusCode(204);

    }

    @Test
    public void deleteTest() {
        VolanteDTO dto = new VolanteDTO("Teste Delete","1234567890123457", 50, 200d, "30",  1L,1L,1L);
        VolanteResponseDTO response = volanteService.create(dto);

        given()
                .when()
                .pathParam("id", response.id())
                .delete("/volantes/{id}")
                .then()
                .statusCode(204);


//        volanteService.delete(id);
//        assertNull(volanteService.getById(response.id()));
    }
}
