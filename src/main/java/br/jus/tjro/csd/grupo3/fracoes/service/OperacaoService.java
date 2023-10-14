package br.jus.tjro.csd.grupo3.fracoes.service;

import br.jus.tjro.csd.grupo3.fracoes.dto.RequisicaoDivisao;
import br.jus.tjro.csd.grupo3.fracoes.exception.ParametroInvalidoException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class OperacaoService {

    public BigDecimal adicao(List<BigDecimal> termos) {

        BigDecimal produto = BigDecimal.valueOf(0);
        for (BigDecimal termo : termos) {

            if(isValorValidoParaAdicao(termo))
                produto = produto.add(termo);
        }
        return produto;
    }
    private boolean isValorValidoParaAdicao(BigDecimal termo) {
        if(isValorZero(termo)){
            throw new ParametroInvalidoException(
                    String.format("Valor da inválido, o termo não pode ter valor igual à %s ",termo));
        }
        return true;
    }

    private boolean isValorZero(BigDecimal termo) {
        return termo.compareTo(BigDecimal.ZERO) == 0;
    }

    private boolean isValorFracionado(BigDecimal termo) {
        BigDecimal inteiro = termo.setScale(0,RoundingMode.DOWN);
        return inteiro.compareTo(termo) != 0;
    }

    public BigDecimal subtracao(List<BigDecimal> termos) {
        BigDecimal produto = BigDecimal.valueOf(0);
        for (BigDecimal termo: termos) {
            if(isValorValidoParaAdicao(termo))
                if(isValorZero(produto)){
                    produto = termo;
                }else{
                    produto = produto.subtract(termo);
                }
        }
        return produto;
    }

    public BigDecimal multiplicacao(List<BigDecimal> termos) {
        BigDecimal produto = BigDecimal.valueOf(1);
        for (BigDecimal termo: termos) {
            if(isValorValidoParaMultiplicacao(termo)){
                produto = produto.multiply(termo);
            }

        }

        return produto;
    }

    private boolean isValorValidoParaMultiplicacao(BigDecimal termo) {
        if(isValorZero(termo)){
            throw new ParametroInvalidoException(
                    String.format("Valor inválido, o termo não pode ter valor igual à %s ",termo));
        }
        return true;
    }

    public BigDecimal divisao(RequisicaoDivisao requisicaoDivisao) {
        BigDecimal produto = BigDecimal.valueOf(1);
        if(isRequisicaoDivisaoValida(requisicaoDivisao)){
            produto = requisicaoDivisao.getNumerador().divide(requisicaoDivisao.getDenominador(),2,RoundingMode.HALF_EVEN);
        }
        return produto;
    }

    private boolean isRequisicaoDivisaoValida(RequisicaoDivisao requisicaoDivisao) {
        if(isValorZero(requisicaoDivisao.getDenominador())){
            throw new ParametroInvalidoException(
                    String.format("Valor inválido, o denomidador não pode ser atribuido o valor igual à %s"
                            ,requisicaoDivisao.getDenominador()));
        }if(isValorFracionado(requisicaoDivisao.getNumerador()) || isValorFracionado(requisicaoDivisao.getDenominador())){
            throw new ParametroInvalidoException(
                    String.format("O Valor do Numerador: %s ou do Denomidador: %s não podem ser frações"
                            ,requisicaoDivisao.getNumerador()
                            ,requisicaoDivisao.getDenominador()
                    )
            );
        }
        return  true;
    }
}
