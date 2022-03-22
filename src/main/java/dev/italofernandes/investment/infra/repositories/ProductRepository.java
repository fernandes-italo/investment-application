package dev.italofernandes.investment.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.italofernandes.investment.domain.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
