package dev.italofernandes.investment.domain.services.facade;

import org.springframework.stereotype.Component;

import dev.italofernandes.investment.domain.services.facade.valueObject.AccountBalanceVO;
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

}
