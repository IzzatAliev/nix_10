package ua.com.alevel.exception;

public class RestBadRequestException extends RestException {

    public RestBadRequestException(String message) {
        super(message);
    }
}
