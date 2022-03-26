package dev.italofernandes.investment.api.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.italofernandes.investment.application.dto.response.ErrorMessageResponse;
import dev.italofernandes.investment.domain.exceptions.AccountIsNotDebitedException;
import dev.italofernandes.investment.domain.exceptions.AccountWithoutBalanceException;
import dev.italofernandes.investment.domain.exceptions.AccountWithoutBalanceForPrivateProductException;

@ControllerAdvice
public class ExceptionControllerHandler {

    private final String ERROR_MESSAGE_DEFAULT = "Não foi possível processar sua requisição!";

    @ExceptionHandler(AccountWithoutBalanceException.class)
    public ResponseEntity<ErrorMessageResponse> errorWithoutBalance(
            AccountWithoutBalanceException exception) {
        return getErrorMessageResponse(
                exception.getMessage(),
                exception.getDescription(),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(AccountWithoutBalanceForPrivateProductException.class)
    public ResponseEntity<ErrorMessageResponse> errorWithoutBalanceForPrivate(
            AccountWithoutBalanceForPrivateProductException exception) {
        return getErrorMessageResponse(
                exception.getMessage(),
                exception.getDescription(),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(AccountIsNotDebitedException.class)
    public ResponseEntity<ErrorMessageResponse> errorIsNotDebited(
            AccountIsNotDebitedException exception) {
        return getErrorMessageResponse(
                exception.getMessage(),
                exception.getDescription(),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageResponse> errorIsNotDebited(
            Exception exception) {
        return getErrorMessageResponse(
                exception.getMessage(),
                ERROR_MESSAGE_DEFAULT,
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private ResponseEntity<ErrorMessageResponse> getErrorMessageResponse(String message, String description,
            HttpStatus httpStatus) {

        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                httpStatus.value(),
                Instant.now(),
                message,
                description);

        return new ResponseEntity<>(errorMessageResponse, httpStatus);
    }
}
