package br.unitins.topicos1;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.GravadoraDTO;
import br.unitins.topicos1.dto.GravadoraResponseDTO;
import br.unitins.topicos1.service.GravadoraService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import static org.hamcrest.CoreMatchers.notNullValue;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GravadoraResourceTest {
    
    @Inject
    GravadoraService gravadoraService;

    @Test
    public void testFindAll() {
        given()
          .when().get("/gravadoras")
          .then()
             .statusCode(200);
    }

    @Test
    public void testInsert() {
        GravadoraDTO gravadoraDTO = new GravadoraDTO("nome1");

        given()
            .contentType(ContentType.JSON)
            .body(gravadoraDTO)
            .when().post("/gravadoras")
            .then()
                .statusCode(201)
                .body("id", notNullValue(), 
                      "nome", is("nome1"));
    }

    @Test
    public void testUpdate() {

        GravadoraDTO gravadoraDTO = new GravadoraDTO("gravadora2");
        
        Long id = gravadoraService.insert(gravadoraDTO).id();

        GravadoraDTO dtoUpdate = new GravadoraDTO("gravadora3");

        given().contentType(ContentType.JSON).body(dtoUpdate).when().put("/gravadoras/" + id).then().statusCode(200);

        GravadoraResponseDTO Gravadora = gravadoraService.findById(id);
        assertThat(Gravadora.nome(), is("gravadora3"));
    
    }

    @Test
    public void testDelete() {
        GravadoraDTO gravadoraDTO = new GravadoraDTO("gravadora4");
        
        GravadoraResponseDTO gravadoraTeste = gravadoraService.insert(gravadoraDTO);

        given().when().delete("/gravadoras/" + gravadoraTeste.id()).then().statusCode(200);

        GravadoraResponseDTO gravadora = gravadoraService.findById(gravadoraTeste.id());
        assertNull(gravadora);
    }

    // @Test
    // public void testFindByLogin() {

    //     GravadoraDTO GravadoraDTO = new GravadoraDTO("nome1");
        
    //     GravadoraResponseDTO GravadoraTeste = GravadoraService.insert(GravadoraDTO);

    //     given().when().get("/Gravadoras/search/login/" + GravadoraTeste.login()).then().statusCode(200);

    // }
    
    @Test
    public void testFindById() {

        GravadoraDTO gravadoraDTO = new GravadoraDTO("gravadora5");
        
        GravadoraResponseDTO gravadoraTeste = gravadoraService.insert(gravadoraDTO);

        given().when().get("/gravadoras/" + gravadoraTeste.id()).then().statusCode(200);
    }

}
