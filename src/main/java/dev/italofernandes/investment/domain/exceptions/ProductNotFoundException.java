package dev.italofernandes.investment.domain.exceptions;

public class ProductNotFoundException extends RuntimeException {

    private String description;

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String message, String description) {
        super(message);
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
