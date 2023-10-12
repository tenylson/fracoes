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

        StringBuilder payload = new StringBuilder();
        payload.append("[")
                .append("1,")
                .append("2,")
                .append("3")
                .append("]");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .port(port)
                .body(payload.toString())
                .post("/operacoes/adicao")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .response();
    }


}
