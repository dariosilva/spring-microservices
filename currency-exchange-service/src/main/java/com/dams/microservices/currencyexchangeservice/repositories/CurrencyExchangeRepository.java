package com.dams.microservices.currencyexchangeservice.repositories;

import com.dams.microservices.currencyexchangeservice.domain.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    CurrencyExchange findByFromAndTo(String from, String to);
}
