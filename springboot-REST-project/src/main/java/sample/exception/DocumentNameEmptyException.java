package sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Document name must not be empty")
public class DocumentNameEmptyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DocumentNameEmptyException(String message) {
        super(message);
    }
}