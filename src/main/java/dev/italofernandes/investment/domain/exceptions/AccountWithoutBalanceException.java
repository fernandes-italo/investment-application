package dev.italofernandes.investment.domain.exceptions;

public class AccountWithoutBalanceException extends RuntimeException {

    private String description;

    public AccountWithoutBalanceException() {
        super();
    }

    public AccountWithoutBalanceException(String message, String description) {
        super(message);
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
