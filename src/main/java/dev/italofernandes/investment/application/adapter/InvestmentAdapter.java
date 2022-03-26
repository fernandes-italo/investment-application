package dev.italofernandes.investment.application.adapter;

import dev.italofernandes.investment.application.dto.response.InvestmentResponse;
import dev.italofernandes.investment.domain.models.Investment;

public class InvestmentAdapter {

    public static InvestmentResponse toDtoInvestment(Investment investment) {
        return new InvestmentResponse(
                investment.getId(),
                investment.getValue(),
                investment.getDate());
    }

}
