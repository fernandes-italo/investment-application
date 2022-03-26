package dev.italofernandes.investment.domain.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dev.italofernandes.investment.domain.exceptions.AccountIsNotDebitedException;
import dev.italofernandes.investment.domain.exceptions.AccountWithoutBalanceException;
import dev.italofernandes.investment.domain.exceptions.AccountWithoutBalanceForPrivateProductException;
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

    @Value("${investment.exceptions.product.without-balance-for-private-product-message}")
    private String accountWithoutBalanceForPrivateProductMessage;

    @Value("${investment.exceptions.product.without-balance-for-private-product-description}")
    private String accountWithoutBalanceForPrivateProductDescription;

    @Value("${investment.exceptions.account.is-not-debited-message}")
    private String accountIsNotDebitedMessage;

    @Value("${investment.exceptions.account.is-not-debited-description}")
    private String accountIsNotDebitedDescription;

    @Override
    public Investment invest(Long productId, Long accountId, Double valueInvestment) {

        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty())
            throw new ProductNotFoundException(
                    productNotFoundMessage,
                    productNotFoundDescription);

        Investment investment = new Investment(productId, accountId, valueInvestment);

        AccountBalanceVO accountBalanceVO = accountFacade.getAccountBalanceById(accountId);

        if (!investment.sufficientBalanceForInvestment(accountBalanceVO.getBalance()))
            throw new AccountWithoutBalanceException(
                    accountWithoutBalanceMessage,
                    accountWithoutBalanceDescription);

        if (!investment.verifyPrivateProductForInvestment(accountBalanceVO.getBalance(), product.get()))
            throw new AccountWithoutBalanceForPrivateProductException(
                    accountWithoutBalanceForPrivateProductMessage,
                    accountWithoutBalanceForPrivateProductDescription);

        boolean isDebited = accountFacade.debitAccount(accountId, valueInvestment);

        if (!isDebited)
            throw new AccountIsNotDebitedException(
                    accountIsNotDebitedMessage,
                    accountIsNotDebitedDescription);

        investmentRepository.save(investment);

        return investment;
    }

}
