package com.br.livepaypedidos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Exceção que indica uma tentativa de persistir um objeto nulo, o que não é permitido.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Construtor que permite fornecer uma mensagem de exceção personalizada.
     *
     * @param ex A mensagem de exceção.
     */
    public RequiredObjectIsNullException(String ex) {
        super(ex);
    }

    /**
     * Construtor padrão com uma mensagem predefinida indicando que não é permitido persistir um objeto nulo.
     */
    public RequiredObjectIsNullException() {
        super("It is not allowed to persist a null object!");
    }

}
