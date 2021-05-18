package com.dams.microservices.currencyconversionservice.proxy;

import com.dams.microservices.currencyconversionservice.domain.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy  {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
