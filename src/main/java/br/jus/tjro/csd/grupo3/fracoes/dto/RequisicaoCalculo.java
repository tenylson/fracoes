package br.jus.tjro.csd.grupo3.fracoes.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class RequisicaoCalculo {
    private List<BigDecimal> termos = new ArrayList<>();


}
