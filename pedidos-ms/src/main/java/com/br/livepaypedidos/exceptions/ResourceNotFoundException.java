package com.br.livepaypedidos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Exceção lançada quando não são encontrados registros com o ID informado.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Construtor que permite fornecer uma mensagem de exceção personalizada.
     *
     * @param msg A mensagem de exceção.
     */
    public ResourceNotFoundException(String msg) {
        super(msg);
    }

    /**
     * Construtor padrão com uma mensagem predefinida indicando que não foram encontrados registros com o ID informado.
     */
    public ResourceNotFoundException() {
        super("Não foi encontrado registros com o ID informado.");
    }
}
