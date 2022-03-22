package dev.italofernandes.investment.domain.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dev.italofernandes.investment.domain.exceptions.ProductNotFoundException;
import dev.italofernandes.investment.domain.models.Investment;
import dev.italofernandes.investment.domain.models.Product;
import dev.italofernandes.investment.domain.services.InvestmentService;
import dev.italofernandes.investment.infra.repositories.InvestmentRepository;
import dev.italofernandes.investment.infra.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final ProductRepository productRepository;

    @Value("${investment.exceptions.product.not-found-message}")
    private String productNotFoundMessage;

    @Value("${investment.exceptions.product.not-found-description}")
    private String productNotFoundDescription;

    @Override
    public Investment invest(Long productId, Long accountId, Double valueInvestment) {

        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty())
            throw new ProductNotFoundException(
                    productNotFoundMessage,
                    productNotFoundDescription);

        Investment investment = new Investment(productId, accountId, valueInvestment);

        return null;
    }

}
