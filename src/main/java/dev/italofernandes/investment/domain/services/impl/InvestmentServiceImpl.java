package dev.italofernandes.investment.domain.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dev.italofernandes.investment.domain.exceptions.AccountWithoutBalanceException;
import dev.italofernandes.investment.domain.exceptions.ProductNotFoundException;
import dev.italofernandes.investment.domain.models.Investment;
import dev.italofernandes.investment.domain.models.Product;
import dev.italofernandes.investment.domain.services.InvestmentService;
import dev.italofernandes.investment.domain.services.facade.AccountFacade;
import dev.italofernandes.investment.domain.services.facade.valueObject.AccountBalanceVO;
import dev.italofernandes.investment.infra.repositories.InvestmentRepository;
import dev.italofernandes.investment.infra.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final ProductRepository productRepository;
    private final AccountFacade accountFacade;

    @Value("${investment.exceptions.product.not-found-message}")
    private String productNotFoundMessage;

    @Value("${investment.exceptions.product.not-found-description}")
    private String productNotFoundDescription;

    @Value("${investment.exceptions.account.without-balance-message}")
    private String accountWithoutBalanceMessage;

    @Value("${investment.exceptions.account.without-balance-description}")
    private String accountWithoutBalanceDescription;

    @Override
    public Investment invest(Long productId, Long accountId, Double valueInvestment) {

        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty())
            throw new ProductNotFoundException(
                    productNotFoundMessage,
                    productNotFoundDescription);

        Investment investment = new Investment(productId, accountId, valueInvestment);

        AccountBalanceVO accountBalanceVO = accountFacade.getAccountBalanceById(accountId);

        if (!investment.suficientBalanceForInvestment(accountBalanceVO.getBalance()))
            throw new AccountWithoutBalanceException(
                    accountWithoutBalanceMessage,
                    accountWithoutBalanceDescription);

        return null;
    }

}
