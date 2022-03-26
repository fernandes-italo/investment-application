package dev.italofernandes.investment.application.dto.response;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageResponse {

    private Integer statusCode;
    private Instant timestamp;
    private String message;
    private String description;

}
