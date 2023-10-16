package br.jus.tjro.csd.grupo3.fracoes.service;

import br.jus.tjro.csd.grupo3.fracoes.dto.RequisicaoCalculo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FracoesService {
    public BigDecimal adicao(RequisicaoCalculo requisicaoCalculo) {
        BigDecimal numerado=BigDecimal.ZERO.stripTrailingZeros();
        BigDecimal denominador=BigDecimal.ZERO.stripTrailingZeros();
        if(isMesmaBase(requisicaoCalculo)){
            numerado = requisicaoCalculo.getPrimeiroTermo().getNumerador().add(requisicaoCalculo.getSegundoTermo().getNumerador());
            denominador = requisicaoCalculo.getSegundoTermo().getDenominador();
        }

        return numerado.divide(denominador);
    }

    private boolean isMesmaBase(RequisicaoCalculo requisicaoCalculo) {
        return requisicaoCalculo.getPrimeiroTermo().getDenominador().compareTo(requisicaoCalculo.getSegundoTermo().getDenominador())==0;
    }

}
