package dev.italofernandes.investment.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.italofernandes.investment.domain.models.Investment;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {

}
