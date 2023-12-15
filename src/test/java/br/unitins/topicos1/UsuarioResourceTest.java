package br.unitins.topicos1;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.dto.EnderecoResponseDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.service.HashService;
import br.unitins.topicos1.service.HashServiceImpl;
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

        
        // hash para gerar a versão hash da senha
        HashService hashService = new HashServiceImpl();
        String senhaHash = hashService.getHashSenha("senha1");

         EnderecoResponseDTO enderecoDTO = new EnderecoResponseDTO("Rua A", "Bairro X", "123", "Apto 456", "12345-678",
            Map.of("nome", "Cidade A", "estado", "Estado X"));

        UsuarioDTO usuarioDTO = new UsuarioDTO("nome3", "login3", "senha1",  enderecoDTO,  "999.999.999-99", Perfil.USER , telefones);

        given()
            .contentType(ContentType.JSON)
            .body(usuarioDTO)
            .when().post("/usuarios")
            .then()
                .statusCode(201)
                .body("id", notNullValue(), 
                      "nome", is("nome3"), 
                      "login", is("login3"), 
                      "senha", is(senhaHash),
                      "listaTelefone[0].codigoArea", is("83"), 
                      "listaTelefone[0].numero", is("999999999"));
    }

    @Test
    public void testUpdate() {
        List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
        telefones.add(new TelefoneDTO("83", "999999999"));

        EnderecoResponseDTO enderecoDTO = new EnderecoResponseDTO("Rua A", "Bairro X", "123", "Apto 456", "12345-678",
            Map.of("nome", "Cidade A", "estado", "Estado X"));


        UsuarioDTO usuarioDTO = new UsuarioDTO("nome8", "login8", "senha1", enderecoDTO,  "999.999.999-99", Perfil.USER ,telefones);

        
        UsuarioResponseDTO usuarioTeste = usuarioService.insert(usuarioDTO);

        UsuarioDTO dtoUpdate = new UsuarioDTO("nome7", "login7", "senha7" ,  enderecoDTO,  "999.999.999-99", Perfil.ADMIN , telefones);

        given().contentType(ContentType.JSON).body(dtoUpdate).when().put("/usuarios/" + usuarioTeste.id()).then().statusCode(204);

        UsuarioResponseDTO usuario = usuarioService.findById(usuarioTeste.id());
        assertThat(usuario.nome(), is("nome7"));
        assertThat(usuario.login(), is("login7"));
        assertThat(usuario.perfil(), is(Perfil.ADMIN));
    
    }

    @Test
    public void testDelete() {
        List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
        telefones.add(new TelefoneDTO("83", "999999999"));

          EnderecoResponseDTO enderecoDTO = new EnderecoResponseDTO("Rua A", "Bairro X", "123", "Apto 456", "12345-678",
            Map.of("nome", "Cidade A", "estado", "Estado X"));

        UsuarioDTO usuarioDTO = new UsuarioDTO("nome5", "login5", "senha1",  enderecoDTO,  "999.999.999-99", Perfil.USER , telefones);
        
        UsuarioResponseDTO usuarioTeste = usuarioService.insert(usuarioDTO);

        given().when().delete("/usuarios/" + usuarioTeste.id()).then().statusCode(204);
    }

    @Test
    public void testFindByLogin() {

        List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
        telefones.add(new TelefoneDTO("83", "999999999"));

          EnderecoResponseDTO enderecoDTO = new EnderecoResponseDTO("Rua A", "Bairro X", "123", "Apto 456", "12345-678",
            Map.of("nome", "Cidade A", "estado", "Estado X"));

        UsuarioDTO usuarioDTO = new UsuarioDTO("nome4", "login4", "senha1", enderecoDTO,  "999.999.999-99", Perfil.USER , telefones);
        
        UsuarioResponseDTO usuarioTeste = usuarioService.insert(usuarioDTO);

        given().when().get("/usuarios/search/login/" + usuarioTeste.login()).then().statusCode(200);

    }
    
    @Test
    public void testFindById() {

        List<TelefoneDTO> telefones = new ArrayList<TelefoneDTO>();
        telefones.add(new TelefoneDTO("83", "999999999"));

          EnderecoResponseDTO enderecoDTO = new EnderecoResponseDTO("Rua A", "Bairro X", "123", "Apto 456", "12345-678",
            Map.of("nome", "Cidade A", "estado", "Estado X"));

        UsuarioDTO usuarioDTO = new UsuarioDTO("nome1", "login", "senha1", enderecoDTO,  "999.999.999-99", Perfil.USER , telefones);
        
        UsuarioResponseDTO usuarioTeste = usuarioService.insert(usuarioDTO);

        given().when().get("/usuarios/" + usuarioTeste.id()).then().statusCode(200);
    }
    
}