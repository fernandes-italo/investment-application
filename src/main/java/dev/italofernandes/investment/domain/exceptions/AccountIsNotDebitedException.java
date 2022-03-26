package dev.italofernandes.investment.domain.exceptions;

public class AccountIsNotDebitedException extends RuntimeException {

    private String description;

    public AccountIsNotDebitedException() {
        super();
    }

    public AccountIsNotDebitedException(String message, String description) {
        super(message);
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
