package br.jus.tjro.csd.grupo3.fracoes.controller;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/operacoes")
public class OperacoesController {

    @PostMapping("/adicao")
    public BigDecimal adicao(@RequestBody List<BigDecimal> termos){
        BigDecimal produto = new BigDecimal(0);
        for (BigDecimal termo:termos) {
            produto = produto.add(termo);
        }
        return produto;
    }
}
