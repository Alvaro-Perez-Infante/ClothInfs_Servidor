package com.example.ClothInfs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción que indica que el recurso solicitado no fue encontrado.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor de la excepción con un mensaje.
     *
     * @param message El mensaje que describe la excepción
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor de la excepción con un mensaje y una causa.
     *
     * @param message El mensaje que describe la excepción
     * @param cause   La causa de la excepción
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
