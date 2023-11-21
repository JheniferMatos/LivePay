package com.br.livepaypag.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Exceção personalizada para indicar que um objeto obrigatório é nulo.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Construtor que recebe uma mensagem de erro personalizada.
     *
     * @param ex Mensagem de erro personalizada.
     */
    public RequiredObjectIsNullException(String ex) {
        super(ex);
    }

    /**
     * Construtor padrão que define uma mensagem de erro padrão.
     */
    public RequiredObjectIsNullException() {
        super("It is not allowed to persist a null object!");
    }

}
