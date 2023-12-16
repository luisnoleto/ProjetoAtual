package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.MunicipioDTO;
import br.unitins.topicos1.dto.MunicipioResponseDTO;
import br.unitins.topicos1.service.MunicipioService;

import jakarta.inject.Inject;

@QuarkusTest
public class MunicipioResourceTest {

    @Inject
    MunicipioService municipioService;

    @Test
    public void getAllTest() {

        given()
                .when().get("/municipios")
                .then()
                .statusCode(200);
    }

    @Test
    public void insertTest() {

        MunicipioDTO municipio = new MunicipioDTO(
                "Miracema do Tocantins", 
                1l);

        given()
                .contentType(ContentType.JSON)
                .body(municipio)
                .when().post("/municipios")
                .then()
                .statusCode(201)
                .body("id", notNullValue(), "nome", is("Miracema do Tocantins"),
                "estado.get(\"nome\")", is("Estado Tocantins"), "estado.sigla", is("TO"));
    }

    @Test //204 - 405
    public void updateTest() {

        MunicipioDTO municipioDto = new MunicipioDTO(
                "Miracema do Tocantins",
                1l);

        Long id = municipioService.insert(municipioDto).id();

        MunicipioDTO municipioUpdate = new MunicipioDTO(
            "Rio Verde",
            1l
        );

        given()
          .contentType(ContentType.JSON)
          .body(municipioUpdate)
          .when().put("/municipios/" + id)
          .then()
             .statusCode(405);

        MunicipioResponseDTO municipioResponse = municipioService.getById(id);

        assertThat(municipioResponse.nome(), is("Miracema do Tocantins"));
        assertThat(municipioResponse.estado().get("nome"), is("Estado Tocantins"));
        assertThat(municipioResponse.estado().get("sigla"), is("TO"));
    }

    @Test
    public void deleteTest() {

        MunicipioDTO municipio = new MunicipioDTO(
            "Miracema do Tocantins",
            1l
        );

        Long id = municipioService.insert(municipio).id();

        given()
          .when().delete("/municipios/" + id)
          .then()
             .statusCode(204);

        MunicipioResponseDTO municipioResponse = null;

        try {
            
            municipioResponse =  municipioService.getById(id);
        } catch (Exception e) {

        }
        finally {
            assertNull(municipioResponse);   
        }
    }


    @Test
    public void getByIdTest() {

        MunicipioDTO municipio = new MunicipioDTO(
            "Miracema do Tocantins",
            1l
        );

        Long id = municipioService.insert(municipio).id();

        given()
            .when().get("/municipios/" + id)
            .then()
                .statusCode(200);
    }

    @Test //200
    public void getByNomeTest() {

        MunicipioDTO municipio = new MunicipioDTO(
            "Miracema do Tocantins",
            1l
        );

        municipioService.insert(municipio);

        String nome = municipio.nome();
        System.out.println("Nome do estado: " + municipio.nome());

        given()
            .when().get("/municipios/searchByNome/" + nome)
            .then()
                .statusCode(200);
    }

    @Test //200 
    public void getByNomeEstadoTest() {

        MunicipioDTO municipio = new MunicipioDTO(
            "Miracema do Tocantins",
            1l
        );


        String nomeEstado = (String) municipioService.insert(municipio).estado().get("nome");

        given()
            .when().get("/municipios/searchByNomeEstado/" + nomeEstado)
            .then()
                .statusCode(200);
    }

    @Test // 200
    public void getBySiglaEstadoTest() {

        MunicipioDTO municipio = new MunicipioDTO(
            "Miracema do Tocantins",
            1l
        );

        String siglaEstado = (String) municipioService.insert(municipio).estado().get("sigla");

        given()
            .when().get("/municipios/searchBySiglaEstado/" + siglaEstado)
            .then()
                .statusCode(200);
    }
}