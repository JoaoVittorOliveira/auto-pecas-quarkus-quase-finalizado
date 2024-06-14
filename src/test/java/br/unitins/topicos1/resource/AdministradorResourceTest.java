package br.unitins.topicos1.resource;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.service.AdministradorService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;

@QuarkusTest
public class AdministradorResourceTest {
    
    @Inject
    AdministradorService service;

    @Test
    public void findAllTest() {
        given()
                .when()
                .get("/administradores")
                .then()
                .statusCode(200);
    }

    @Test
    public void findByIdTest() {
        given()
                .when()
                .get("/administradores/search/id/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void findByNomeTest() {
        given()
                .when()
                .get("/administradores/search/nome/Nome inexistente")
                .then()
                .statusCode(200)
                .body("nomeCompleto", not("Nome que existe"));
    }

    @Test
    public void findByCpfTest() {
        given()
                .when()
                .get("/administradores/search/cpf/111.222.111-22")
                .then()
                .statusCode(200)
                .body("cpf", is("111.222.111-22"));
    }

    @Test
    public void findByUsernameTest() {
        given()
                .when()
                .get("/administradores/search/username/fernando")
                .then()
                .statusCode(200)
                .body("username", is("fernando"));
    }
}
