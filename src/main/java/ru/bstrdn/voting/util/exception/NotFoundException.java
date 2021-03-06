package ru.bstrdn.voting.util.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException {

    int idResource;

    public NotFoundException(int idResource, String message) {
        super(message);
        this.idResource = idResource;
    }
}