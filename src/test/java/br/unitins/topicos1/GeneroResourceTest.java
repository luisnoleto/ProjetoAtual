package br.unitins.topicos1;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.GeneroDTO;
import br.unitins.topicos1.dto.GeneroResponseDTO;
import br.unitins.topicos1.service.GeneroService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import static org.hamcrest.CoreMatchers.notNullValue;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GeneroResourceTest {

    @Inject
    GeneroService generoService;

    @Test
    public void testFindAll() {
        given()
          .when().get("/generos")
          .then()
             .statusCode(200);
    }

    @Test
    public void testInsert() {
        GeneroDTO generoDTO = new GeneroDTO("genero1");

        given()
            .contentType(ContentType.JSON)
            .body(generoDTO)
            .when().post("/generos")
            .then()
                .statusCode(201)
                .body("id", notNullValue(), 
                      "nome", is("genero1"));
    }

    @Test
    public void testUpdate() {

        GeneroDTO generoDTO = new GeneroDTO("genero3");
        

        Long id = generoService.insert(generoDTO).id();

        GeneroDTO dtoUpdate = new GeneroDTO("generoupdate");

        given().contentType(ContentType.JSON).body(dtoUpdate).when().put("/generos/" + id).then().statusCode(200);

        GeneroResponseDTO genero = generoService.findById(id);
        assertThat(genero.nome(), is("generoupdate"));
    
    }

    @Test
    public void testDelete() {
        GeneroDTO generoDTO = new GeneroDTO("generodelete");
        
        GeneroResponseDTO generoTeste = generoService.insert(generoDTO);

        given().when().delete("/generos/" + generoTeste.id()).then().statusCode(200);

         GeneroResponseDTO genero = generoService.findById(generoTeste.id());
         assertNull(genero);
        // assertThat(genero, is(notNullValue()));
        

    }

    @Test
    public void testFindById() {

        GeneroDTO generoDTO = new GeneroDTO("generoupdate");
        
        GeneroResponseDTO generoTeste = generoService.insert(generoDTO);

        given().when().get("/generos/" + generoTeste.id()).then().statusCode(200);
    }
    
}
