package dev.italofernandes.investment.domain.models;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Long accountId;
    private Double value;

    @CreationTimestamp
    private Instant date;

    private Boolean investmentPrivate;

    public Investment(Long productId, Long accountId, Double value) {
        this.productId = productId;
        this.accountId = accountId;
        this.value = value;
    }

    public Boolean suficientBalanceForInvestment(Double accountBalance) {
        return this.value < accountBalance;
    }

}
