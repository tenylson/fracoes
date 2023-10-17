package br.jus.tjro.csd.grupo3.fracoes.controller;

import br.jus.tjro.csd.grupo3.fracoes.dto.RequisicaoCalculo;
import br.jus.tjro.csd.grupo3.fracoes.service.OperacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/operacoes")
public class OperacoesController {

    @Autowired
    private OperacaoService operacaoService;

    @PostMapping("/adicao")
    public BigDecimal adicao(@RequestBody List<BigDecimal> termos){
        return operacaoService.adicao(termos);
    }

    @PostMapping("/subtracao")
    public BigDecimal subtracao(@RequestBody List<BigDecimal> termos){
        return operacaoService.subtracao(termos);
    }

    @PostMapping("/multiplicacao")
    public BigDecimal muliplicacao(@RequestBody List<BigDecimal> termos){
        return operacaoService.multiplicacao(termos);
    }

    @PostMapping("/divisao")
    public BigDecimal divisao(@RequestBody RequisicaoCalculo requisicaoDivisao){
        return operacaoService.divisao(requisicaoDivisao);
    }
}
