package dev.italofernandes.investment.infra.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dev.italofernandes.investment.domain.services.facade.valueObject.AccountBalanceVO;

@FeignClient(name = "${investment.paths.client-account.name}", url = "${investment.paths.client-account.base-url}")
public interface AccountClient {

    @GetMapping("${investment.paths.client-account.balance-url}")
    AccountBalanceVO getAccountBalance(@PathVariable("accountId") Long accountId);

}
