package br.jus.tjro.csd.grupo3.fracoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;



@EqualsAndHashCode
public class Fracao {

    public Fracao(int numerador, int denominador){
        setNumerador(BigDecimal.valueOf(numerador));
        setDenominador(BigDecimal.valueOf(denominador));
        simplificar();
    }

    private void simplificar() {
        int limite=0;
        if(numerador.compareTo(denominador) < 0){
            limite = numerador.intValue();
        }else {
            limite = denominador.intValue();
        }
        for (int i = limite; i > 0 ; i--) {
            BigDecimal numeradorTemp = getNumerador().divide(BigDecimal.valueOf(i),4,RoundingMode.HALF_UP);
            BigDecimal denominadorTemp = getDenominador().divide(BigDecimal.valueOf(i),4,RoundingMode.HALF_UP);
            if(isValorInteiro(numeradorTemp) &&
                    isValorInteiro(denominadorTemp)
            ){
                setNumerador(numeradorTemp);
                setDenominador(denominadorTemp);
                break;
            }
        }
    }

    private boolean isValorInteiro(BigDecimal termo) {
        BigDecimal inteiro = termo.setScale(0, RoundingMode.DOWN);
        return inteiro.compareTo(termo) == 0;
    }


    @Setter
    private BigDecimal numerador;
    @Setter
    private BigDecimal denominador;

    public BigDecimal getNumerador() {
        return numerador.setScale(0);
    }

    public BigDecimal getDenominador() {
        return denominador.setScale(0);
    }
}
