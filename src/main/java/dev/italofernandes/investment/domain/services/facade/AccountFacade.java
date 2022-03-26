package dev.italofernandes.investment.domain.services.facade;

import dev.italofernandes.investment.domain.services.facade.valueObject.AccountBalanceVO;

public interface AccountFacade {

    AccountBalanceVO getAccountBalanceById(Long accountId);

    Boolean debitAccount(Long accountId, Double valueOfInvestment);

}
