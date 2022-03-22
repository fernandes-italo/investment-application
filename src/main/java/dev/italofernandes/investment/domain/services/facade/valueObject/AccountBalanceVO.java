package dev.italofernandes.investment.domain.services.facade.valueObject;

import lombok.Data;

@Data
public class AccountBalanceVO {

    private Long accountId;
    private Double balance;

}
