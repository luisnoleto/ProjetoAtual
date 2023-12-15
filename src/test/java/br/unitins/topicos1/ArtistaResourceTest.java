
package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import br.unitins.topicos1.dto.ArtistaDTO;
import br.unitins.topicos1.dto.ArtistaResponseDTO;
import br.unitins.topicos1.service.ArtistaService;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;


@QuarkusTest
public class ArtistaResourceTest {

    @Inject
    ArtistaService artistaService;

    @Test
    public void testFindAll() {
        given()
          .when().get("/artistas")
          .then()
             .statusCode(200);
    }

    @Test
    public void testInsert() {

        ArtistaDTO ArtistaDTO = new ArtistaDTO("nome1", "descricao1");

        given()
            .contentType(ContentType.JSON)
            .body(ArtistaDTO)
            .when().post("/artistas")
            .then()
                .statusCode(201)
                .body("nome", is("nome1"), 
                      "descricao", is("descricao1"));
    }


    @Test
    public void testUpdate() {
        ArtistaDTO artistaDTO = new ArtistaDTO("nome", "descricao");
        
        ArtistaResponseDTO artistaTeste = artistaService.insert(artistaDTO);

        ArtistaDTO dtoUpdate = new ArtistaDTO("nome2", "descricao2");

        given().contentType(ContentType.JSON).body(dtoUpdate).when().put("/artistas/" + artistaTeste.id()).then().statusCode(204);

        ArtistaResponseDTO Artista = artistaService.findById(artistaTeste.id());
        assertThat(Artista.nome(), is("nome2"));
        assertThat(Artista.descricao(), is("descricao2"));
      
    
    }

    @Test
    public void testDelete(){
         ArtistaDTO artistaDTO = new ArtistaDTO("nome", "descricao");
        
        ArtistaResponseDTO artistaTeste = artistaService.insert(artistaDTO);

        assertThat(artistaService.findById(artistaTeste.id()), is(notNullValue()));
        given().when().delete("/artistas/" + artistaTeste.id()).then().statusCode(204);
    }

    @Test
    public void testFindById() {
        ArtistaDTO artistaDTO = new ArtistaDTO("nome", "descricao");
        
        ArtistaResponseDTO artistaTeste = artistaService.insert(artistaDTO);

        given()
            .when().get("/artistas/" + artistaTeste.id())
            .then()
                .statusCode(200);
    }

    @Test //nao funciona
    public void findByName() { 
        ArtistaDTO artistaDTO = new ArtistaDTO("nome", "descricao");
        
        ArtistaResponseDTO artistaTeste = artistaService.insert(artistaDTO);

        given()
            .when().get("/artistas/search/nome/" + artistaTeste.nome())
            .then()
                .statusCode(200);
    }

}