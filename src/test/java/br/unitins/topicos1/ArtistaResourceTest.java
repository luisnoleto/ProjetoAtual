
package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import br.unitins.topicos1.dto.TelefoneDTO;
import java.util.ArrayList;
import java.util.List;
import br.unitins.topicos1.dto.ArtistaDTO;
import br.unitins.topicos1.dto.ArtistaResponseDTO;
import br.unitins.topicos1.service.ArtistaService;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


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
        List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
        telefones.add(new TelefoneDTO("83", "999999999"));

        ArtistaDTO ArtistaDTO = new ArtistaDTO("nome", "descricao");

        given()
            .contentType(ContentType.JSON)
            .body(ArtistaDTO)
            .when().post("/Artistas")
            .then()
                .statusCode(201)
                .body("nome", is("nome1"), 
                      "descricao", is("login"));
    }


    @Test
    public void testUpdate() {
        ArtistaDTO ArtistaDTO = new ArtistaDTO("nome", "descricao");
        
        ArtistaResponseDTO ArtistaTeste = ArtistaService.insert(ArtistaDTO);

        ArtistaDTO dtoUpdate = new ArtistaDTO("nome2", "descricao2");

        given().contentType(ContentType.JSON).body(dtoUpdate).when().put("/Artistas/" + ArtistaTeste.id()).then().statusCode(204);

        ArtistaResponseDTO Artista = ArtistaService.findById(ArtistaTeste.id());
        assertThat(Artista.nome(), is("nome2"));
        assertThat(Artista.descricao2(), is("descicao2"));
        //assertThat(Artista.login(), is("login2"));
    
    }

}