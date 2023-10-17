package br.jus.tjro.csd.grupo3.fracoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
public class Fracao {

    private BigDecimal numerador;
    private BigDecimal denominador;

}
