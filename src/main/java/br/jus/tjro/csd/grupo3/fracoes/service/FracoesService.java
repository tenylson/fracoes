package br.jus.tjro.csd.grupo3.fracoes.service;

import br.jus.tjro.csd.grupo3.fracoes.dto.Fracao;
import br.jus.tjro.csd.grupo3.fracoes.dto.RequisicaoCalculo;
import br.jus.tjro.csd.grupo3.fracoes.exception.ParametroInvalidoException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FracoesService {
    public Fracao adicao(RequisicaoCalculo requisicaoCalculo) {
        validaRequisicao(requisicaoCalculo);
        BigDecimal numerado=BigDecimal.ZERO.stripTrailingZeros();
        BigDecimal denominador=BigDecimal.ZERO.stripTrailingZeros();
        if(isMesmaBase(requisicaoCalculo)){
            numerado = requisicaoCalculo.getPrimeiroTermo().getNumerador().add(requisicaoCalculo.getSegundoTermo().getNumerador());
            denominador = requisicaoCalculo.getSegundoTermo().getDenominador();
        }else {
            return somaBaseDiferente(requisicaoCalculo);
        }

        return new Fracao(numerado.intValue(),denominador.intValue());
    }

    private void validaRequisicao(RequisicaoCalculo requisicaoCalculo) {
        if(isFracaoTemZero(requisicaoCalculo.getPrimeiroTermo())){
            throw new ParametroInvalidoException(String.format("A fração não pode ter zero %s",requisicaoCalculo.getPayload()));
        }
        if(isFracaoTemZero(requisicaoCalculo.getSegundoTermo())){
            throw new ParametroInvalidoException(String.format("A fração não pode ter zero %s",requisicaoCalculo.getPayload()));
        }
    }

    private boolean isFracaoTemZero(Fracao fracao) {
        return fracao.getDenominador().compareTo(BigDecimal.ZERO) ==0 ||  fracao.getNumerador().compareTo(BigDecimal.ZERO) ==0;
    }

    private Fracao somaBaseDiferente(RequisicaoCalculo requisicaoCalculo) {
        BigDecimal numeradorPrimeiro = requisicaoCalculo.getPrimeiroTermo().getNumerador().multiply(requisicaoCalculo.getSegundoTermo().getDenominador());
        BigDecimal numeradorSegundo = requisicaoCalculo.getSegundoTermo().getNumerador().multiply(requisicaoCalculo.getPrimeiroTermo().getDenominador());
        BigDecimal denomiador = requisicaoCalculo.getPrimeiroTermo().getDenominador().multiply(requisicaoCalculo.getSegundoTermo().getDenominador());
        numeradorPrimeiro = numeradorPrimeiro.add(numeradorSegundo);
        return new Fracao(numeradorPrimeiro.intValue(),denomiador.intValue());
    }

    private boolean isMesmaBase(RequisicaoCalculo requisicaoCalculo) {
        return requisicaoCalculo.getPrimeiroTermo().getDenominador().compareTo(requisicaoCalculo.getSegundoTermo().getDenominador())==0;
    }

    public Fracao subtracao(RequisicaoCalculo requisicaoCalculo) {
        Fracao seundoTermo = requisicaoCalculo.getSegundoTermo();
        seundoTermo.setNumerador(seundoTermo.getNumerador().multiply(BigDecimal.valueOf(-1)));
        requisicaoCalculo.setSegundoTermo(seundoTermo);

        return adicao(requisicaoCalculo);
    }

    public Fracao multiplicacao(RequisicaoCalculo requisicaoCalculo) {
        validaRequisicao(requisicaoCalculo);
        BigDecimal numerador = requisicaoCalculo.getPrimeiroTermo().getNumerador().multiply(requisicaoCalculo.getSegundoTermo().getNumerador());
        BigDecimal denomidador = requisicaoCalculo.getPrimeiroTermo().getDenominador().multiply(requisicaoCalculo.getSegundoTermo().getDenominador());
        return new Fracao(numerador.intValue(), denomidador.intValue());
    }

    public Fracao divisao(RequisicaoCalculo requisicaoCalculo) {
        BigDecimal numerador = requisicaoCalculo.getSegundoTermo().getDenominador();
        BigDecimal denomidador = requisicaoCalculo.getSegundoTermo().getNumerador();
        requisicaoCalculo.setSegundoTermo(new Fracao(numerador.intValue(),denomidador.intValue()));
        return multiplicacao(requisicaoCalculo);
    }

}
