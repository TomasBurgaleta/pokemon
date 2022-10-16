package com.pokemon.store.domain.error;

public class NoEntityException extends RuntimeException {

    public NoEntityException(String message) {
        super(message);
    }

}
