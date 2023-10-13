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
        RestAssured.given()
                .contentType(ContentType.JSON)
                .port(port)
                .body("[1,2,3]")
                .post("/operacoes/adicao")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .response();
    }

    @Test
    public void testarAdcaoComValorZero(){
        RestAssured.given()
                .contentType(ContentType.JSON)
                .port(port)
                .body("[1,0]")
                .post("/operacoes/adicao")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response();
    }

    @Test
    public void testaSubtracaoComSucesso(){
        RestAssured.given()
                .contentType(ContentType.JSON)
                .port(port)
                .body("[10,2,3]")
                .post("/operacoes/subtracao")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .response();
    }

    @Test
    public void testarSubtracaoComValorZero(){
        RestAssured.given()
                .contentType(ContentType.JSON)
                .port(port)
                .body("[1,0]")
                .post("/operacoes/subtracao")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response();
    }


}
