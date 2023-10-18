package br.jus.tjro.csd.grupo3.fracoes.controller;

import br.jus.tjro.csd.grupo3.fracoes.dto.Fracao;
import br.jus.tjro.csd.grupo3.fracoes.dto.RequisicaoCalculo;
import br.jus.tjro.csd.grupo3.fracoes.service.FracoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/fracoes")
public class FracoesController {


    @Autowired
    private FracoesService fracoesService;

    @PostMapping("/adicao")
    public Fracao adicao(@RequestBody RequisicaoCalculo requisicaoCalculo){
        return fracoesService.adicao(requisicaoCalculo);
    }

    @PostMapping("/subtracao")
    public Fracao subtracao(@RequestBody RequisicaoCalculo requisicaoCalculo){
        return fracoesService.subtracao(requisicaoCalculo);
    }

    @PostMapping("/multiplicacao")
    public Fracao mutiplicacao(@RequestBody RequisicaoCalculo requisicaoCalculo){
        return fracoesService.multiplicacao(requisicaoCalculo);
    }

    @PostMapping("/divisao")
    public Fracao divisao(@RequestBody RequisicaoCalculo requisicaoCalculo){
        return fracoesService.divisao(requisicaoCalculo);
    }

}
