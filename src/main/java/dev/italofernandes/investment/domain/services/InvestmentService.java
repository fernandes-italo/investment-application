package dev.italofernandes.investment.domain.services;

import dev.italofernandes.investment.domain.models.Investment;

public interface InvestmentService {

    Investment invest(Long productId, Long accountId, Double valueInvestment);

}
