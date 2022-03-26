package dev.italofernandes.investment.application;

import dev.italofernandes.investment.application.dto.request.InvestmentRequest;
import dev.italofernandes.investment.application.dto.response.InvestmentResponse;

public interface InvestmentApplication {

    InvestmentResponse invest(Long accountId, InvestmentRequest investmentRequest);

}
