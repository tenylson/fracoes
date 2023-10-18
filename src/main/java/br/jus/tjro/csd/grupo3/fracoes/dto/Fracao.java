package br.jus.tjro.csd.grupo3.fracoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class Fracao {

    public Fracao(int numerador, int denominador){
        setNumerador(BigDecimal.valueOf(numerador));
        setDenominador(BigDecimal.valueOf(denominador));
    }

    private BigDecimal numerador;
    private BigDecimal denominador;

}
