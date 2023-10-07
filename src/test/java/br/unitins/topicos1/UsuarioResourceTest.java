package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;
import br.unitins.topicos1.dto.TelefoneDTO;
import java.util.ArrayList;
import java.util.List;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.service.UsuarioService;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UsuarioResourceTest {

    @Inject
    UsuarioService usuarioService;

    @Test
    public void testFindAll() {
        given()
          .when().get("/usuarios")
          .then()
             .statusCode(200);
    }

    @Test
    public void testInsert() {
        List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
        telefones.add(new TelefoneDTO("83", "999999999"));

        UsuarioDTO usuarioDTO = new UsuarioDTO("nome1", "login", "senha1", true, telefones);

        given()
            .contentType(ContentType.JSON)
            .body(usuarioDTO)
            .when().post("/usuarios")
            .then()
                .statusCode(201)
                .body("id", notNullValue(), 
                      "nome", is("nome1"), 
                      "login", is("login"), 
                      "senha", is("senha1"), 
                      "isAdmin", is(true), 
                      "listaTelefone[0].codigoArea", is("83"), 
                      "listaTelefone[0].numero", is("999999999"));
    }

    @Test
    public void testUpdate() {
        List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
        telefones.add(new TelefoneDTO("83", "999999999"));
        UsuarioDTO usuarioDTO = new UsuarioDTO("nome1", "login", "senha1", true, telefones);
        
        UsuarioResponseDTO usuarioTeste = usuarioService.insert(usuarioDTO);

        UsuarioDTO dtoUpdate = new UsuarioDTO("nome2", "login2", "senha2", false, telefones);

        given().contentType(ContentType.JSON).body(dtoUpdate).when().put("/usuarios/" + usuarioTeste.id()).then().statusCode(204);

        UsuarioResponseDTO usuario = usuarioService.findById(usuarioTeste.id());
        assertThat(usuario.nome(), is("nome2"));
        assertThat(usuario.login(), is("login2"));
        assertThat(usuario.senha(), is("senha2"));
        assertThat(usuario.isAdmin(), is(false));
    
    }

    @Test
    public void testDelete() {
        List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
        telefones.add(new TelefoneDTO("83", "999999999"));
        UsuarioDTO usuarioDTO = new UsuarioDTO("nome1", "login", "senha1", true, telefones);
        
        UsuarioResponseDTO usuarioTeste = usuarioService.insert(usuarioDTO);

        given().when().delete("/usuarios/" + usuarioTeste.id()).then().statusCode(204);
    }

    @Test
    public void testFindByLogin() {

        List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
        telefones.add(new TelefoneDTO("83", "999999999"));
        UsuarioDTO usuarioDTO = new UsuarioDTO("nome1", "login", "senha1", true, telefones);
        
        UsuarioResponseDTO usuarioTeste = usuarioService.insert(usuarioDTO);

        given().when().get("/usuarios/search/login/" + usuarioTeste.login()).then().statusCode(500);

    }
    
    @Test
    public void testFindById() {

        List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
        telefones.add(new TelefoneDTO("83", "999999999"));
        UsuarioDTO usuarioDTO = new UsuarioDTO("nome1", "login", "senha1", true, telefones);
        
        UsuarioResponseDTO usuarioTeste = usuarioService.insert(usuarioDTO);

        given().when().get("/usuarios/" + usuarioTeste.id()).then().statusCode(200);
    }
    
}