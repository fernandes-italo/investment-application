package dev.italofernandes.investment.domain.exceptions;

public class AccountWithoutBalanceForPrivateProductException extends RuntimeException {

    private String description;

    public AccountWithoutBalanceForPrivateProductException() {
        super();
    }

    public AccountWithoutBalanceForPrivateProductException(String message, String description) {
        super(message);
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
