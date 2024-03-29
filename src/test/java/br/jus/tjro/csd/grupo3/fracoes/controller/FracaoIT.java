package br.jus.tjro.csd.grupo3.fracoes.controller;

import br.jus.tjro.csd.grupo3.fracoes.dto.Fracao;
import br.jus.tjro.csd.grupo3.fracoes.dto.RequisicaoCalculo;
import br.jus.tjro.csd.grupo3.fracoes.service.FracoesService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FracaoIT {

    @LocalServerPort
    private int port;

    @Autowired
    private FracoesService fracoesService;

    @Test
    public void testaAdicaoMesmaBase(){
        Fracao primeroTermo = new Fracao(2,4);
        Fracao segundoTermo = new Fracao(3,4);
        RequisicaoCalculo payload = new RequisicaoCalculo(primeroTermo,segundoTermo);

        post(payload.getPayload(),"/fracoes/adicao", HttpStatus.OK.value());

        Fracao resultado = new Fracao(5,4);

        Assertions.assertTrue(fracoesService.adicao(payload).equals(resultado));
    }


    @Test
    public void testaAdicaoBaseDiferente(){
        Fracao primeroTermo = new Fracao(2,4);
        Fracao segundoTermo = new Fracao(3,5);
        RequisicaoCalculo payload = new RequisicaoCalculo(primeroTermo,segundoTermo);

        post(payload.getPayload(),"/fracoes/adicao", HttpStatus.OK.value());

        Fracao resultado = new Fracao(22,20);

        Assertions.assertTrue(fracoesService.adicao(payload).equals(resultado));

    }

    @Test
    public void testaAdicaoComFracaoContendoZero(){
        Fracao primeroTermo = new Fracao(2,0);
        Fracao segundoTermo = new Fracao(3,5);
        RequisicaoCalculo payload = new RequisicaoCalculo(primeroTermo,segundoTermo);

        post(payload.getPayload(),"/fracoes/adicao", HttpStatus.BAD_REQUEST.value());
    }


    @Test
    public void testaSubtracaoComSucesso(){
        Fracao primeroTermo = new Fracao(7,2);
        Fracao segundoTermo = new Fracao(10,3);
        RequisicaoCalculo payload = new RequisicaoCalculo(primeroTermo,segundoTermo);

        post(payload.getPayload(),"/fracoes/subtracao", HttpStatus.OK.value());

        Fracao resultado = new Fracao(1,6);

        Assertions.assertTrue(fracoesService.subtracao(payload).equals(resultado));

    }

    @Test
    public void testaMuliplicacaoComSucesso(){
        Fracao primeroTermo = new Fracao(2,3);
        Fracao segundoTermo = new Fracao(5,4);
        RequisicaoCalculo payload = new RequisicaoCalculo(primeroTermo,segundoTermo);

        post(payload.getPayload(),"/fracoes/multiplicacao", HttpStatus.OK.value());

        Fracao resultado = new Fracao(10,12);

        Assertions.assertTrue(fracoesService.multiplicacao(payload).equals(resultado));


    }

    @Test
    public void testaDivisaoComSucesso(){
        Fracao primeroTermo = new Fracao(2,7);
        Fracao segundoTermo = new Fracao(1,3);
        RequisicaoCalculo payload = new RequisicaoCalculo(primeroTermo,segundoTermo);

        post(payload.getPayload(),"/fracoes/divisao", HttpStatus.OK.value());

        Fracao resultado = new Fracao(6,7);

        Assertions.assertTrue(fracoesService.divisao(payload).equals(resultado));


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

    @Test
    public void testaSimplificacao(){
        Fracao resultado = new Fracao(12,30);
        Fracao simplificado = new Fracao(2,5);
        Assertions.assertTrue(simplificado.equals(resultado));
    }
}


