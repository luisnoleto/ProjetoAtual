/*package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import br.unitins.topicos1.dto.AlbumDTO;
import br.unitins.topicos1.dto.AlbumResponseDTO;
import br.unitins.topicos1.dto.ArtistaDTO;
import br.unitins.topicos1.dto.GeneroDTO;
import br.unitins.topicos1.dto.GravadoraDTO;
import br.unitins.topicos1.service.AlbumService;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import  br.unitins.topicos1.model.TipoProduto;


@QuarkusTest
public class AlbumResourceTest {

    @Inject
    AlbumService albumService;

    @Test
    public void testFindAll() {
        given()
          .when().get("/albuns")
          .then()
             .statusCode(200);
    }

    @Test
    public void testInsert() {
        ArtistaDTO artistaDTO = new ArtistaDTO("nome1", "descricao1");
        GeneroDTO generoDTO = new GeneroDTO("nome1");
        GravadoraDTO gravadoraDTO = new GravadoraDTO("nome1");
    
        AlbumDTO albumDTO = new AlbumDTO("Kisses", "2019", "Kisses é bla bla bla", 20.00, TipoProduto.CD, 20, 1L, 1L, 1L);
    
        given()
            .contentType(ContentType.JSON)
            .body(albumDTO)
            .when().post("/albuns")
            .then()
                .statusCode(201)
                .body("nome", is("Kisses"), 
                      "descricao", is("Kisses é bla bla bla"), 
                      "preco", is(20.00f), 
                      "tipoProduto", is("CD"),
                      "estoque", is(20), 
                      "artista.id", is(1), 
                      "genero.id", is(1), 
                      "gravadora.id", is(1)); 
    }
    

    @Test
public void testUpdate() {

      ArtistaDTO artistaDTO = new ArtistaDTO("nome1", "descricao1");
        GeneroDTO generoDTO = new GeneroDTO("nome1");
        GravadoraDTO gravadoraDTO = new GravadoraDTO("nome1");

       
        ArtistaDTO artistaDTO2 = new ArtistaDTO("nome2", "descricao2");
        GeneroDTO generoDTO2 = new GeneroDTO("nome2");
        GravadoraDTO gravadoraDTO2 = new GravadoraDTO("nome2");
        
    AlbumDTO albumDTO = new AlbumDTO("Kisses", "2019", "Kisses é bla bla bla", 20.00, TipoProduto.CD, 20, 1L, 1L, 1L);

    AlbumResponseDTO albumTeste = albumService.insert(albumDTO);

  
    AlbumDTO dtoUpdate = new AlbumDTO("Kisses 2.0", "2020", "Nova descrição", 25.00, TipoProduto.VINIL, 30, 2L, 2L, 2L);

   
    given()
        .contentType(ContentType.JSON)
        .body(dtoUpdate)
        .when()
        .put("/albuns/" + albumTeste.id())
        .then()
        .statusCode(200);

    AlbumResponseDTO novoAlbum = albumService.findById(albumTeste.id());
    assertThat(novoAlbum.nome(), is("Kisses 2.0"));
    assertThat(novoAlbum.anoLancamento(), is("2020"));
    assertThat(novoAlbum.descricao(), is("Nova descrição"));
    assertThat(novoAlbum.preco(), is(25.00));
    assertThat(novoAlbum.tipoProduto(), is(TipoProduto.VINIL));
    assertThat(novoAlbum.estoque(), is(30));
    assertThat(novoAlbum.artista().id(), is(2L));
    assertThat(novoAlbum.genero().id(), is(2L));
    assertThat(novoAlbum.gravadora().id(), is(2L));
}


    @Test
    public void testDelete(){
        ArtistaDTO artistaDTO = new ArtistaDTO("nome1", "descricao1");
        GeneroDTO generoDTO = new GeneroDTO("nome1");
        GravadoraDTO gravadoraDTO = new GravadoraDTO("nome1");
    
        AlbumDTO albumDTO = new AlbumDTO("Kisses", "2019", "Kisses é bla bla bla", 20.00, TipoProduto.CD, 20, 1L, 1L, 1L);
        
        AlbumResponseDTO albumTeste = albumService.insert(albumDTO);
      
        given().when().delete("/albuns/" + albumTeste.id()).then().statusCode(200);
    }

    @Test // não está funcionando
    public void testFindById() {
        ArtistaDTO artistaDTO = new ArtistaDTO("nome1", "descricao1");
        GeneroDTO generoDTO = new GeneroDTO("nome1");
        GravadoraDTO gravadoraDTO = new GravadoraDTO("nome1");
    
        AlbumDTO albumDTO = new AlbumDTO("Kisses", "2019", "Kisses é bla bla bla", 20.00, TipoProduto.CD, 20, 1L, 1L, 1L);
        
        AlbumResponseDTO albumTeste = albumService.insert(albumDTO);
        given()
            .when().get("/albuns/" + albumTeste.id())
            .then()
                .statusCode(200);
    }

    @Test
    public void findByName() {
       ArtistaDTO artistaDTO = new ArtistaDTO("nome1", "descricao1");
        GeneroDTO generoDTO = new GeneroDTO("nome1");
        GravadoraDTO gravadoraDTO = new GravadoraDTO("nome1");
    
        AlbumDTO albumDTO = new AlbumDTO("Kisses", "2019", "Kisses é bla bla bla", 20.00, TipoProduto.CD, 20, 1L, 1L, 1L);
        
        AlbumResponseDTO albumTeste = albumService.insert(albumDTO);

        given()
            .when().get("/albuns/search/nome/" + albumTeste.nome())
            .then()
                .statusCode(200);
    }

}
*/
