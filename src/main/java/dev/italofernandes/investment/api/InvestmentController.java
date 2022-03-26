package dev.italofernandes.investment.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.italofernandes.investment.application.InvestmentApplication;
import dev.italofernandes.investment.application.dto.request.InvestmentRequest;
import dev.italofernandes.investment.application.dto.response.InvestmentResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class InvestmentController {

    private final InvestmentApplication investmentApplication;

    @PostMapping("/investment/{accountId}")
    public ResponseEntity<InvestmentResponse> invest(
            @PathVariable Long accountId,
            @RequestBody InvestmentRequest investmentRequest) {

        InvestmentResponse investmentResponse = investmentApplication.invest(accountId, investmentRequest);

        return ResponseEntity.ok().body(investmentResponse);
    }

}
