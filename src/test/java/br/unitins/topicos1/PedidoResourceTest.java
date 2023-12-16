package br.unitins.topicos1;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Test;
import br.unitins.topicos1.service.PedidoService;
import jakarta.inject.Inject;

public class PedidoResourceTest {

    @Inject
    PedidoService pedidoService;

    @Test
    public void testFindAll() {
        given()
          .when().get("/pedidos")
          .then()
             .statusCode(200);
    }


    
}
