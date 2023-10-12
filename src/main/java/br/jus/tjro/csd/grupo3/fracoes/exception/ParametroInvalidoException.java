package br.jus.tjro.csd.grupo3.fracoes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ParametroInvalidoException extends RuntimeException {
    public ParametroInvalidoException(String mensagem){
        super(mensagem);
    }
}
