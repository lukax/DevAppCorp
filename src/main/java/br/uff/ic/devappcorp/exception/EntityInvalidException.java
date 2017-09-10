package br.uff.ic.devappcorp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityInvalidException extends RuntimeException {
    public EntityInvalidException() {
        super();
    }
    public EntityInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
    public EntityInvalidException(String message) {
        super(message);
    }
    public EntityInvalidException(Throwable cause) {
        super(cause);
    }
}