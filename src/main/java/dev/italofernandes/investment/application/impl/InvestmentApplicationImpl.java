package dev.italofernandes.investment.application.impl;

import org.springframework.stereotype.Component;

import dev.italofernandes.investment.application.InvestmentApplication;
import dev.italofernandes.investment.application.adapter.InvestmentAdapter;
import dev.italofernandes.investment.application.dto.request.InvestmentRequest;
import dev.italofernandes.investment.application.dto.response.InvestmentResponse;
import dev.italofernandes.investment.domain.models.Investment;
import dev.italofernandes.investment.domain.services.InvestmentService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InvestmentApplicationImpl implements InvestmentApplication {

    private final InvestmentService investmentService;

    @Override
    public InvestmentResponse invest(Long accountId, InvestmentRequest investmentRequest) {
        Investment investment = investmentService.invest(
                investmentRequest.getProductId(),
                accountId,
                investmentRequest.getValue());

        return InvestmentAdapter.toDtoInvestment(investment);
    }

}
