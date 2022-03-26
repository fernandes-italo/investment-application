package dev.italofernandes.investment.domain.services.facade.impl;

import org.springframework.stereotype.Component;

import dev.italofernandes.investment.application.dto.request.AccountDebitRequest;
import dev.italofernandes.investment.domain.services.facade.AccountFacade;
import dev.italofernandes.investment.domain.services.facade.valueObject.AccountBalanceVO;
import dev.italofernandes.investment.domain.services.facade.valueObject.AccountDebitVO;
import dev.italofernandes.investment.infra.http.AccountClient;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountFacadeImpl implements AccountFacade {

    private final AccountClient accountClient;

    @Override
    public AccountBalanceVO getAccountBalanceById(Long accountId) {

        AccountBalanceVO accountBalanceVO = accountClient.getAccountBalance(accountId);
        return accountBalanceVO;
    }

    @Override
    public Boolean debitAccount(Long accountId, Double valueOfInvestment) {
        AccountDebitVO accountDebitVO = accountClient.debit(accountId, new AccountDebitRequest(valueOfInvestment));

        return accountDebitVO.isDebited();
    }

}
