package br.jus.tjro.csd.grupo3.fracoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequisicaoCalculo {
    private Fracao primeiroTermo;
    private Fracao segundoTermo;

    public String getPayload(){
        StringBuilder payload = new StringBuilder();
        payload.append("{")
                .append("    \"primeiroTermo\":{")
                .append("        \"numerador\":"+primeiroTermo.getNumerador()+",\n")
                .append("        \"denominador\":"+primeiroTermo.getDenominador()+"\n")
                .append("    },")
                .append("\"segundoTermo\":{\n" )
                .append("        \"numerador\":"+segundoTermo.getNumerador()+",\n")
                .append("        \"denominador\":"+segundoTermo.getDenominador()+"\n" )
                .append("    }\n" )
                .append("}")
        ;
        return  payload.toString();
    }
}
