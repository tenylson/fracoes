package br.jus.tjro.csd.grupo3.fracoes.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OperacoesTI {

    @LocalServerPort
    private int port;


    @Test
    public void testaAdicaoComSucesso(){
        post("[1,2,3]","/operacoes/adicao",HttpStatus.OK.value());
    }

    @Test
    public void testarAdcaoComValorZero(){
        post("[1,0]","/operacoes/adicao",HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void testaSubtracaoComSucesso(){
        post("[10,2,3]","/operacoes/subtracao",HttpStatus.OK.value());
    }

    @Test
    public void testarSubtracaoComValorZero(){
        post("[1,0]","/operacoes/subtracao",HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void testaMultiplicacaoComSucesso(){
        post("[10,2,3]","/operacoes/multiplicacao",HttpStatus.OK.value());
    }

    @Test
    public void testaMultiplicacaoComValorZero(){
        post("[1,0,3]","/operacoes/multiplicacao",HttpStatus.BAD_REQUEST.value());
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
    public void testaDivisaoComSucesso(){
        post("{\"numerador\":10,\"denominador\":2}"
        ,"/operacoes/divisao"
        ,HttpStatus.OK.value());
    }

    @Test
    public void testaDivisaoComDenonimadorZero(){
        post("{\"numerador\":10,\"denominador\":0}"
                ,"/operacoes/divisao"
                ,HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void testaDivisaoComFracao(){
        post("{\"numerador\":0.10,\"denominador\":1}"
                ,"/operacoes/divisao"
                ,HttpStatus.BAD_REQUEST.value());
    }


}
