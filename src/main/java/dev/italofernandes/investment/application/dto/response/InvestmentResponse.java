package dev.italofernandes.investment.application.dto.response;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvestmentResponse {

    private Long id;
    private Double value;
    private Instant date;

}
