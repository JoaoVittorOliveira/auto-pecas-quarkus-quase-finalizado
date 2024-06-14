package br.unitins.topicos1.resource;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.ClienteDTO;
import br.unitins.topicos1.dto.ClienteResponseDTO;
import br.unitins.topicos1.dto.InjecaoDTO;
import br.unitins.topicos1.dto.InjecaoResponseDTO;
import br.unitins.topicos1.dto.PasswordUpdateDTO;
import br.unitins.topicos1.dto.UsernameUpdateDTO;
import br.unitins.topicos1.service.ClienteService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;

import java.time.LocalDateTime;

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

    @Test
    public void createTest(){

        String username = LocalDateTime.now() + "_useer";
        String nome = LocalDateTime.now() + "_nome";

        ClienteDTO dto = new ClienteDTO(
                            nome, 
                            "sobrenome", 
                            "555.555.555-55", 
                            20, 
                            10, 
                            2010, 
                            username, 
                            "123456789", 
                            "testeunitario@gmail.com"
                         );

        System.out.println(dto);

        given()
        .contentType(MediaType.APPLICATION_JSON)
        .body(dto)
            .when()
                .post("/clientes")
            .then()
                .statusCode(200)
                .body("nomeCompleto", is( nome+" sobrenome"));
    }

    @Test
    @TestSecurity(user = "tester", roles = "Administrador")
    public void deleteTest() {
        String username = LocalDateTime.now() + "_useer";
        String nome = LocalDateTime.now() + "_nome";
        ClienteDTO dto = new ClienteDTO(
                            nome, 
                            "sobrenome", 
                            "555.555.555-55", 
                            20, 
                            10, 
                            2010, 
                            username, 
                            "123456789", 
                            "testeunitario@gmail.com"
         );
         ClienteResponseDTO response = service.create(dto);

        given()
                .when()
                .pathParam("id", response.id())
                .delete("/clientes/{id}")
                .then()
                .statusCode(204);
    }

    @Test
    @TestSecurity(user = "tester", roles = "Administrador")
    public void updateTest(){
        ClienteDTO dto = new ClienteDTO(
                            "lerolero", 
                            "sobrenome", 
                            "555.555.555-55", 
                            20, 
                            10, 
                            2010, 
                            "AAAAA", 
                            "123", 
                            "testeunitario@gmail.com"
                         );

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .pathParam("id", 1)
            .put("/clientes/{id}")
        .then()
            .statusCode(204);
    }

    @Test
    @TestSecurity(user = "tester", roles = "Cliente")
    public void updateUsuarioPasswordTest(){

        PasswordUpdateDTO dto = new PasswordUpdateDTO("123", "novaSENHA");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .pathParam("idUsuario", 1)
            .patch("/clientes/update-password/{idUsuario}")
        .then()
            .statusCode(204);
    }
    
    @Test
    @TestSecurity(user = "tester", roles = "Cliente")
    public void updateUsuarioUsernameTest(){

        UsernameUpdateDTO dto = new UsernameUpdateDTO("novoUSERNAME");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .pathParam("idUsuario", 1)
            .patch("/clientes/update-username/{idUsuario}")
        .then()
            .statusCode(204);
    }
    
}
