package com.dams.microservices.currencyexchangeservice.controller;

import com.dams.microservices.currencyexchangeservice.domain.CurrencyExchange;
import com.dams.microservices.currencyexchangeservice.repositories.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    Logger log = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private CurrencyExchangeRepository repository;

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    private CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        log.info("Retrieve Exchange Value called with {} to {}", from, to);
        var currencyExchange = repository.findByFromAndTo(from, to);
        if (currencyExchange == null) {
            throw new RuntimeException("Unable to find data for " + from + " to "+ to);
        }
        var port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}
