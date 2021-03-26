package ru.bstrdn.voting.util.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncorrectDataException extends RuntimeException {

    public IncorrectDataException(String message) {
        super(message);
    }
}