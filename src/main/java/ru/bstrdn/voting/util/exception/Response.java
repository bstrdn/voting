package ru.bstrdn.voting.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private int id;
    private String message;
    private String[] details;

    public Response(int id, String message) {
        this(0, message, null);
    }
    public Response(String message, String... details) {
        this(0, message, details);

    }
}