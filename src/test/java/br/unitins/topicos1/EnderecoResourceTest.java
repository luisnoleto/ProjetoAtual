package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.service.EnderecoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EnderecoResourceTest {

    @Inject
    JsonWebToken jwt;

    @Inject
    EnderecoService enderecoService;

    @Test //200-401 nao autorizado
    public void getAllTest() {
        //String login = jwt.getSubject();

        given()
                .when().get("/endereco")
                .then()
                .statusCode(200);
    }

    @Test //201 - 401 nao autorizado
    public void insertTest() {

        EnderecoDTO enderecoDTO = new EnderecoDTO(
                "Rua das Flores", "Pasquale", "15", "Perto da Pracinha",
                 "58660-320", 1l, "Palmas", "TO");

        given()
                .contentType(ContentType.JSON)
                .body(enderecoDTO)
                .when().post("/endereco/insere-endereco")
                .then()
                .statusCode(401)
                .body("id", notNullValue(), "logradouro", is("Rua das Flores"),
                "bairro", is("Pasquale"), "numero", is("15"), "complemento", is("Perto da Pracinha"),
                "cep", is("58660-320"), "municipio.get(\"nome\")", is("Cidade de Palmas"), 
                "cidade", is("Palmas"), "estado", is("TO"));
    }




    
}
