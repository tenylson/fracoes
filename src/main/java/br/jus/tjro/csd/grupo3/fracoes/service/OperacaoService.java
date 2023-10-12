package br.jus.tjro.csd.grupo3.fracoes.service;

import br.jus.tjro.csd.grupo3.fracoes.exception.ParametroInvalidoException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class OperacaoService {

    public BigDecimal adicao(List<BigDecimal> termos) {

        BigDecimal produto = new BigDecimal(0);
        for (BigDecimal termo : termos) {

            if(isValorFracionado(termo)){
                throw new ParametroInvalidoException(String.format("Valor da inválido %s para operção adição, não pode havar valor fracionado",termo));
            }



            produto = produto.add(termo);
        }
        return produto;
    }

    private boolean isValorFracionado(BigDecimal termo) {
        BigDecimal inteiro = termo.setScale(0,RoundingMode.DOWN);
        return inteiro.compareTo(termo) != 0;
    }
}
