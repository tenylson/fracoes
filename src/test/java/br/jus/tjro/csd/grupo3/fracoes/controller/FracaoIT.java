package br.jus.tjro.csd.grupo3.fracoes.controller;

import br.jus.tjro.csd.grupo3.fracoes.dto.Fracao;
import br.jus.tjro.csd.grupo3.fracoes.dto.RequisicaoCalculo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FracaoIT {

    @LocalServerPort
    private int port;

    @Test
    public void testaAdicaoMesmaBase(){
        Fracao primeroTermo = new Fracao(BigDecimal.valueOf(2),BigDecimal.valueOf(4));
        Fracao segundoTermo = new Fracao(BigDecimal.valueOf(3),BigDecimal.valueOf(4));
        RequisicaoCalculo payload = new RequisicaoCalculo(primeroTermo,segundoTermo);

        post(payload.getPayload(),"/fracoes/adicao", HttpStatus.OK.value());
    }


    @Test
    public void testaAdicaoBaseDiferente(){
        Fracao primeroTermo = new Fracao(BigDecimal.valueOf(2),BigDecimal.valueOf(4));
        Fracao segundoTermo = new Fracao(BigDecimal.valueOf(3),BigDecimal.valueOf(5));
        RequisicaoCalculo payload = new RequisicaoCalculo(primeroTermo,segundoTermo);

        post(payload.getPayload(),"/fracoes/adicao", HttpStatus.OK.value());
    }

    @Test
    public void testaAdicaoComFracaoContendoZero(){
        Fracao primeroTermo = new Fracao(BigDecimal.valueOf(2),BigDecimal.valueOf(0));
        Fracao segundoTermo = new Fracao(BigDecimal.valueOf(3),BigDecimal.valueOf(5));
        RequisicaoCalculo payload = new RequisicaoCalculo(primeroTermo,segundoTermo);

        post(payload.getPayload(),"/fracoes/adicao", HttpStatus.BAD_REQUEST.value());
    }


    @Test
    public void testaSubtracaoComSucesso(){
        Fracao primeroTermo = new Fracao(BigDecimal.valueOf(7),BigDecimal.valueOf(2));
        Fracao segundoTermo = new Fracao(BigDecimal.valueOf(10),BigDecimal.valueOf(3));
        RequisicaoCalculo payload = new RequisicaoCalculo(primeroTermo,segundoTermo);

        post(payload.getPayload(),"/fracoes/subtracao", HttpStatus.OK.value());
    }

    @Test
    public void testaMuliplicacaoComSucesso(){
        Fracao primeroTermo = new Fracao(BigDecimal.valueOf(2),BigDecimal.valueOf(3));
        Fracao segundoTermo = new Fracao(BigDecimal.valueOf(5),BigDecimal.valueOf(4));
        RequisicaoCalculo payload = new RequisicaoCalculo(primeroTermo,segundoTermo);

        post(payload.getPayload(),"/fracoes/multiplicacao", HttpStatus.OK.value());
    }



    private void post(String payload, String url, int httpStatus){
        RestAssured.given()
                .contentType(ContentType.JSON)
                .port(port)
                .body(payload)
                .post(url)
                .then()
                .statusCode(httpStatus)
                .extract()
                .response();
    }
}


