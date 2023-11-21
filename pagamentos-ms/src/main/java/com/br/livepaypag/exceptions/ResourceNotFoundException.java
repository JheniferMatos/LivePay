package com.br.livepaypag.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Exceção personalizada para indicar que um recurso não foi encontrado.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Construtor que recebe uma mensagem de erro personalizada.
     *
     * @param msg Mensagem de erro personalizada.
     */
    public ResourceNotFoundException(String msg) {
        super(msg);
    }

    /**
     * Construtor padrão que define uma mensagem de erro padrão.
     */
    public ResourceNotFoundException() {
        super("Não foi encontrado registros com o ID informado.");
    }
}
