package br.unitins.topicos1.resource;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.AdministradorDTO;
import br.unitins.topicos1.dto.AdministradorResponseDTO;
import br.unitins.topicos1.service.AdministradorService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;

import java.time.LocalDateTime;

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

    @Test
    public void createTest(){

        String username = LocalDateTime.now() + "_useer";
        String nome = LocalDateTime.now() + "_nome";

        AdministradorDTO dto = new AdministradorDTO(
                            nome, 
                            "sobrenome", 
                            "555.555.555-55", 
                            20, 
                            10, 
                            2010, 
                            username, 
                            "123456789" 
                         );

        System.out.println(dto);

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
            .when()
                .post("/administradores")
            .then()
                .statusCode(200)
                .body("nome", is( nome));
    }

    @Test
    // @TestSecurity(user = "tester", roles = "Administrador")
    public void deleteTest() {
        String username = LocalDateTime.now() + "_useer";
        String nome = LocalDateTime.now() + "_nome";
        AdministradorDTO dto = new AdministradorDTO(
                            nome, 
                            "sobrenome", 
                            "555.555.555-55", 
                            20, 
                            10, 
                            2010, 
                            username, 
                            "123456789" 
                         );
         AdministradorResponseDTO response = service.create(dto);

        given()
                .when()
                .pathParam("id", response.id())
                .delete("/administradores/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    // @TestSecurity(user = "tester", roles = "Administrador")
    public void updateTest(){
        AdministradorDTO dto = new AdministradorDTO(
                            "lerolero", 
                            "sobrenome", 
                            "555.555.555-55", 
                            20, 
                            10, 
                            2010, 
                            "lerolero", 
                            "123" 
                         );

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .pathParam("id", 1)
            .put("/administradores/{id}")
        .then()
            .statusCode(204);
    }
}
