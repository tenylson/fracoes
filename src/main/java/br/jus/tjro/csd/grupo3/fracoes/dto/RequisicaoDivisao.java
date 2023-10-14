package br.jus.tjro.csd.grupo3.fracoes.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class RequisicaoDivisao {

    private BigDecimal numerador;
    private BigDecimal denominador;

}
