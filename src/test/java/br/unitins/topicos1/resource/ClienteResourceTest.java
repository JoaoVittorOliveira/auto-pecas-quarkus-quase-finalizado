package br.unitins.topicos1.resource;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.service.ClienteService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;

@QuarkusTest
public class ClienteResourceTest {
    
    @Inject
    ClienteService service;

    @Test
    public void findAllTest() {
        given()
                .when()
                .get("/clientes")
                .then()
                .statusCode(200);
    }

    @Test
    public void findByIdTest() {
        given()
                .when()
                .get("/clientes/search/id/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void findByNomeTest() {
        given()
                .when()
                .get("/clientes/search/nome/Nome inexistente")
                .then()
                .statusCode(200)
                .body("nomeCompleto", not("Nome que existe"));
    }

    @Test
    public void findByCpfTest() {
        given()
                .when()
                .get("/clientes/search/cpf/111.111.111-11")
                .then()
                .statusCode(200)
                .body("cpf", is("111.111.111-11"));
    }

    @Test
    public void findByUsernameTest() {
        given()
                .when()
                .get("/clientes/search/username/teste")
                .then()
                .statusCode(200)
                .body("username", is("teste"));
    }

}
